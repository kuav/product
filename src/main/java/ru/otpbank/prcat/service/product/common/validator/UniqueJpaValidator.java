package ru.otpbank.prcat.service.product.common.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.otpbank.prcat.service.product.common.CommonUtils;
import ru.otpbank.prcat.service.product.common.exception.ValidatorInitializingException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueJpaValidator implements ConstraintValidator<UniqueJpa, Object> {

    @Autowired
    private EntityManager em;
    private Class<?> entityClass;
    private String[] entityFields;
    private String[] objectFields;
    private String idField;

    public void initialize(UniqueJpa constraint) {
        entityClass = constraint.entityClass();
        entityFields = constraint.entityFields();
        objectFields = constraint.fields();
        idField = constraint.idField();
    }

    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        Object idValue = getIdValue(obj);
        Object[] fieldValues = getFieldsValues(obj);

        CriteriaQuery<?> cq = getCriteriaQuery(idValue, fieldValues);

        TypedQuery<?> query = em.createQuery(cq);
        return query.getResultList().isEmpty();
    }

    private CriteriaQuery<?> getCriteriaQuery(Object idValue, Object[] fieldValues) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<?> cq = cb.createQuery(entityClass);

        Root<?> entityRoot = cq.from(entityClass);
        Predicate predicate = getFieldsPredicate(cb, fieldValues, entityRoot);
        Predicate idPredicate = getIdPredicate(cb, entityRoot, idValue);
        if (idPredicate != null)
            predicate = cb.and(predicate, idPredicate);

        cq.where(predicate);
        return cq;
    }

    private Object getIdValue(Object obj) {
        return StringUtils.isEmpty(idField) ? null : getValue(obj, idField);
    }

    private Object[] getFieldsValues(Object obj) {
        Object[] fieldValues = new Object[objectFields.length];
        int i = 0;
        for (String fieldName : objectFields) {
            fieldValues[i++] = getValue(obj, fieldName);
        }
        return fieldValues;
    }

    private Predicate getFieldsPredicate(CriteriaBuilder cb, Object[] fieldValues, Root<?> entityRoot) {
        Predicate fieldsPredicate = null;
        int i = 0;
        for (String entityField : entityFields) {
            Predicate fieldPredicate = cb.equal(entityRoot.get(entityField), fieldValues[i]);
            if (fieldsPredicate == null)
                fieldsPredicate = fieldPredicate;
            else fieldsPredicate = cb.and(fieldsPredicate, fieldPredicate);
        }
        return fieldsPredicate;
    }

    private Predicate getIdPredicate(CriteriaBuilder cb, Root<?> entityRoot, Object idValue) {
        if (idValue == null)
            return null;

        EntityType<?> type = em.getMetamodel().entity(entityClass);
        final Class<?> idType = type.getIdType().getJavaType();
        SingularAttribute<?, ?> idAttribute = type.getId(idType);
        Predicate idPredicate = cb.notEqual(entityRoot.get(idAttribute.getName()), idValue);
        return idPredicate;
    }

    private Object getValue(Object obj, String fieldName) {
        try {
            return CommonUtils.getValue(obj, fieldName);
        } catch (IllegalAccessException e) {
            throw new ValidatorInitializingException("UniqueValidator initialize error: can not extract " + fieldName + " value from " + obj.getClass().getName());
        }
    }
}
