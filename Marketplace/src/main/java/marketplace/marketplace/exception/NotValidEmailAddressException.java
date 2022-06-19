package marketplace.marketplace.exception;


import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NotValidEmailAddressException extends AbstractThrowableProblem {
    public NotValidEmailAddressException(String s) {
        super(URI.create("users/invalid_email"), "Invalid email address!", Status.BAD_REQUEST, String.format("Invalid email address: %s", s));
    }
}
