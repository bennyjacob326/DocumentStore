package se_practice.week2.registrar.registrar.query;

/**
 * Query objects are used to search our data. The only required field in any query is the ID of the user making the query.
 * Because all subclasses must allow for any combination of their fields to be set or left null, no other field
 * is required to be non-null even in many of the subclasses. However, any fields in subclasses that are primitive
 * types must be initialized to a default "empty" value (e.g. -1 for int) to avoid the appearance of being set
 * when in fact they are not.
 *
 * If all fields in a query subclass are null, that means select ALL instances of the type of data the given query
 * searches for.
 *
 * Query instances can't simply take an "example" model instance to match (as opposed to a bunch of individual fields)
 * because:
 * 1) we want to allow combinations that would be illogical for a single instance of model classes
 * 1a) we would not be able to do the assertions etc. that we should do on model classes if they are to also be used for queries
 */
public class Query {

    private final int userID;

    /**
     * Create a query object to specify to the system what information you want
     * @param userID the user submitting the query
     */
    public Query(int userID) {
        this.userID = userID;
    }

    /**
     * Identify the user making the query so we can determine if he has permission to read/write the data in question
     * @return the ID of the user who is making this query
     */
    public int getUserID(){
        return this.userID;
    }




}