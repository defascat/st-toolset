package com.softteco.toolset.mail;

/**
 *
 * @author serge
 */
public class MultiMailProcessor implements MailProcessor {

    private final MailService mailService;
    private int count;

    public MultiMailProcessor(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void add(final String email, final String subject, final String body) {
        mailService.send(email, subject, body);
    }

    @Override
    public void add(final MailTemplate template) {
        add(template.getTo(), template.getSubject(), template.getBody());
    }

    @Override
    public void close() {
    }

    @Override
    public String getResult() {
        return count + " mails were handled.";
    }
}
