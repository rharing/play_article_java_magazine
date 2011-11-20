package util;

import play.data.validation.Check;
import play.db.jpa.Model;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Checks to see if there already exists a certain model in the database.
 * How this should be checked should be defined in the subclasses.
 */
public abstract class UniqueObjectCheck<T extends Model> extends Check {

	@Override
	@SuppressWarnings("unchecked")
	public boolean isSatisfied(final Object validatedObject, final Object value) {
		boolean checkOk = true;
		Class modelType = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		if (validatedObject != null && modelType.isAssignableFrom(validatedObject.getClass())) {
		    T item = (T) validatedObject;
			List<T> existingObjects = loadExistingUniqueObjects(item);
			checkOk = existingObjects.size() == 0;
			if (existingObjects.size() == 1) {
				if (item.id != null) {
					T existingClass = existingObjects.get(0);
					if (existingClass.getId().equals(item.id)) {
						checkOk = true;
					}
				}
			}
		}
		return checkOk;
	}

	protected abstract List<T> loadExistingUniqueObjects(final T item);
}
