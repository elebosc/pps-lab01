package example.model.api;

/**
 * This class represents the account holder concept.
 * That is: a person that can subscribe a bank account.
 * <p>
 * Each account holder has a name, a surname and an ID (unique in the bank system)
 */
public record AccountHolder(String name, String surname, int id) { }
