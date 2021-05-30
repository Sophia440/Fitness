package com.epam.web.command;

/**
 * Represents the command result.
 */
public class CommandResult {
    private final boolean isRedirect;
    private final String page;

    public CommandResult(boolean isRedirect, String page) {
        this.isRedirect = isRedirect;
        this.page = page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public String getPage() {
        return page;
    }


    /**
     * Forwards a page.
     *
     * @param page a page to forward
     * @return CommandResult with a false isRedirect flag
     */
    public static CommandResult forward(String page) {
        return new CommandResult(false, page);
    }

    /**
     * Redirects a page.
     *
     * @param page a page to redirect
     * @return CommandResult with a true isRedirect flag
     */
    public static CommandResult redirect(String page) {
        return new CommandResult(true, page);
    }
}
