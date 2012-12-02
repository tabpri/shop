// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: SpringDao.vsl in andromda-spring-cartridge.
//
package net.malta.model;

/**
 * @see net.malta.model.StaticData
 */
public interface StaticDataDao
{
    /**
     * This constant is used as a transformation flag; entities can be converted automatically into value objects
     * or other types, different methods in a class implementing this interface support this feature: look for
     * an <code>int</code> parameter called <code>transform</code>.
     * <p/>
     * This specific flag denotes no transformation will occur.
     */
    public final static int TRANSFORM_NONE = 0;

    /**
     * Loads an instance of net.malta.model.StaticData from the persistent store.
     */
    public net.malta.model.StaticData load(java.lang.Integer id);

    /**
     * <p>
     * Does the same thing as {@link #load(java.lang.Integer)} with an
     * additional flag called <code>transform</code>. If this flag is set to <code>TRANSFORM_NONE</code> then
     * the returned entity will <strong>NOT</strong> be transformed. If this flag is any of the other constants
     * defined in this class then the result <strong>WILL BE</strong> passed through an operation which can
     * optionally transform the entity (into a value object for example). By default, transformation does
     * not occur.
     * </p>
     *
     * @param id the identifier of the entity to load.
     * @return either the entity or the object transformed from the entity.
     */
    public Object load(int transform, java.lang.Integer id);

    /**
     * Loads all entities of type {@link net.malta.model.StaticData}.
     *
     * @return the loaded entities.
     */
    public java.util.Collection loadAll();

    /**
     * <p>
     * Does the same thing as {@link #loadAll()} with an
     * additional flag called <code>transform</code>. If this flag is set to <code>TRANSFORM_NONE</code> then
     * the returned entity will <strong>NOT</strong> be transformed. If this flag is any of the other constants
     * defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     * transform the entity (into a value object for example). By default, transformation does
     * not occur.
     * </p>
     *
     * @param transform the flag indicating what transformation to use.
     * @return the loaded entities.
     */
    public java.util.Collection loadAll(final int transform);

    /**
     * Creates an instance of net.malta.model.StaticData and adds it to the persistent store.
     */
    public net.malta.model.StaticData create(net.malta.model.StaticData staticData);

    /**
     * <p>
     * Does the same thing as {@link #create(net.malta.model.StaticData)} with an
     * additional flag called <code>transform</code>. If this flag is set to <code>TRANSFORM_NONE</code> then
     * the returned entity will <strong>NOT</strong> be transformed. If this flag is any of the other constants
     * defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     * transform the entity (into a value object for example). By default, transformation does
     * not occur.
     * </p>
     */
    public Object create(int transform, net.malta.model.StaticData staticData);

    /**
     * Creates a new instance of net.malta.model.StaticData and adds
     * from the passed in <code>entities</code> collection
     *
     * @param entities the collection of net.malta.model.StaticData
     * instances to create.
     *
     * @return the created instances.
     */
    public java.util.Collection create(java.util.Collection entities);

    /**
     * <p>
     * Does the same thing as {@link #create(net.malta.model.StaticData)} with an
     * additional flag called <code>transform</code>. If this flag is set to <code>TRANSFORM_NONE</code> then
     * the returned entity will <strong>NOT</strong> be transformed. If this flag is any of the other constants
     * defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     * transform the entities (into value objects for example). By default, transformation does
     * not occur.
     * </p>
     */
    public java.util.Collection create(int transform, java.util.Collection entities);

    /**
     * <p>
     * Creates a new <code>net.malta.model.StaticData</code>
     * instance from <strong>all</strong> attributes and adds it to
     * the persistent store.
     * </p>
     */
    public net.malta.model.StaticData create(
        java.lang.String fromaddress,
        java.lang.String sitename,
        java.lang.String basepath,
        boolean unix,
        int mapeventspan,
        byte[] noimage,
        int carriage,
        java.lang.String creditcardprocesurl,
        java.lang.String contract_code);

    /**
     * <p>
     * Does the same thing as {@link #create(java.lang.String, java.lang.String, java.lang.String, boolean, int, byte[], int, java.lang.String, java.lang.String)} with an
     * additional flag called <code>transform</code>. If this flag is set to <code>TRANSFORM_NONE</code> then
     * the returned entity will <strong>NOT</strong> be transformed. If this flag is any of the other constants
     * defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     * transform the entity (into a value object for example). By default, transformation does
     * not occur.
     * </p>
     */
    public Object create(
        int transform,
        java.lang.String fromaddress,
        java.lang.String sitename,
        java.lang.String basepath,
        boolean unix,
        int mapeventspan,
        byte[] noimage,
        int carriage,
        java.lang.String creditcardprocesurl,
        java.lang.String contract_code);


    /**
     * Updates the <code>staticData</code> instance in the persistent store.
     */
    public void update(net.malta.model.StaticData staticData);

    /**
     * Updates all instances in the <code>entities</code> collection in the persistent store.
     */
    public void update(java.util.Collection entities);

    /**
     * Removes the instance of net.malta.model.StaticData from the persistent store.
     */
    public void remove(net.malta.model.StaticData staticData);

    /**
     * Removes the instance of net.malta.model.StaticData having the given
     * <code>identifier</code> from the persistent store.
     */
    public void remove(java.lang.Integer id);

    /**
     * Removes all entities in the given <code>entities<code> collection.
     */
    public void remove(java.util.Collection entities);

}
