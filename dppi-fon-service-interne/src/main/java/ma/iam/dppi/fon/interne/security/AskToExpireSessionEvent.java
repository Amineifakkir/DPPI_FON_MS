package ma.iam.dppi.fon.interne.security;

import org.springframework.context.ApplicationEvent;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class AskToExpireSessionEvent extends ApplicationEvent {

    private static final long serialVersionUID = -1915691753338712193L;

    public AskToExpireSessionEvent(final Object source) {
        super(source);
    }

    @Override
    public String getSource() {
       return (String)super.getSource();
    }


    public static AskToExpireSessionEvent of(final String sessionId) {
       return new AskToExpireSessionEvent(sessionId);
    }

}
