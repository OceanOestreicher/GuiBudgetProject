package gui.components.interfaces;
/*
Interface for any form a user fills out.

Form data is returned as 1 string where individual entries are separated
by <> and blank entries by <N/A>

Example:
Ocean<>12-31-22<><N/A><>$1400<><N/A>
 */
public interface Form {

    String getFormData();
    boolean hasValidFormData();
}
