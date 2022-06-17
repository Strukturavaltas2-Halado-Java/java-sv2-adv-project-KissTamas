package marketplace.marketplace.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class AdNotFoundException extends AbstractThrowableProblem {
    public AdNotFoundException(Long id) {
        super(URI.create("ads/not-found"), "Not found", Status.NOT_FOUND, String.format("Ad not found: %d", id));
    }
}

