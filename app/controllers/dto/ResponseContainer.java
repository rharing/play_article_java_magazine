package controllers.dto;

import play.data.validation.Error;
import play.i18n.Messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * General dto for json responses.
 */
public class ResponseContainer {
	public String status;
	public String message;
	public Map<String, String> errors = new HashMap<String,String>();

	public ResponseContainer(final String status, final String message) {
		this.status = status;
		this.message = message;
	}

    /**
     * Add the status and the errors to the response container. The error's keys will be updated
     * where the dots are replaced with underscores so that they will match the html generated id's
     * @param status should not be ok since this is a constructor with errors
     * @param errors validation errors to display
     */
	public ResponseContainer(final String status, final List<Error> errors) {
		this.status = status;
		for (Error error : errors) {
			this.errors.put(error.getKey().replaceAll("\\.", "_"), Messages.get(error.message()));
		}
	}
}
