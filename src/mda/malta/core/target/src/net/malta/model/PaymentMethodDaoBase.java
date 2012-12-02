// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: SpringHibernateDaoBase.vsl in andromda-spring-cartridge.
//
package net.malta.model;

/**
 * <p>
 * Base Spring DAO Class: is able to create, update, remove, load, and find
 * objects of type <code>net.malta.model.PaymentMethod</code>.
 * </p>
 *
 * @see net.malta.model.PaymentMethod
 */
public abstract class PaymentMethodDaoBase
    extends org.springframework.orm.hibernate3.support.HibernateDaoSupport
    implements net.malta.model.PaymentMethodDao
{

    /**
     * @see net.malta.model.PaymentMethodDao#load(int, java.lang.Integer)
     */
    public java.lang.Object load(final int transform, final java.lang.Integer id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.load - 'id' can not be null");
        }
        final java.lang.Object entity = this.getHibernateTemplate().get(net.malta.model.PaymentMethodImpl.class, id);
        return transformEntity(transform, (net.malta.model.PaymentMethod)entity);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#load(java.lang.Integer)
     */
    public net.malta.model.PaymentMethod load(java.lang.Integer id)
    {
        return (net.malta.model.PaymentMethod)this.load(TRANSFORM_NONE, id);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#loadAll()
     */
    public java.util.Collection loadAll()
    {
        return this.loadAll(TRANSFORM_NONE);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#loadAll(int)
     */
    public java.util.Collection loadAll(final int transform)
    {
        final java.util.Collection results = this.getHibernateTemplate().loadAll(net.malta.model.PaymentMethodImpl.class);
        this.transformEntities(transform, results);
        return results;
    }


    /**
     * @see net.malta.model.PaymentMethodDao#create(net.malta.model.PaymentMethod)
     */
    public net.malta.model.PaymentMethod create(net.malta.model.PaymentMethod paymentMethod)
    {
        return (net.malta.model.PaymentMethod)this.create(TRANSFORM_NONE, paymentMethod);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#create(int transform, net.malta.model.PaymentMethod)
     */
    public java.lang.Object create(final int transform, final net.malta.model.PaymentMethod paymentMethod)
    {
        if (paymentMethod == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.create - 'paymentMethod' can not be null");
        }
        this.getHibernateTemplate().save(paymentMethod);
        return this.transformEntity(transform, paymentMethod);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#create(java.util.Collection)
     */
    public java.util.Collection create(final java.util.Collection entities)
    {
        return create(TRANSFORM_NONE, entities);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#create(int, java.util.Collection)
     */
    public java.util.Collection create(final int transform, final java.util.Collection entities)
    {
        if (entities == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.create - 'entities' can not be null");
        }
        this.getHibernateTemplate().execute(
            new org.springframework.orm.hibernate3.HibernateCallback()
            {
                public java.lang.Object doInHibernate(org.hibernate.Session session)
                    throws org.hibernate.HibernateException
                {
                    for (java.util.Iterator entityIterator = entities.iterator(); entityIterator.hasNext();)
                    {
                        create(transform, (net.malta.model.PaymentMethod)entityIterator.next());
                    }
                    return null;
                }
            },
            true);
        return entities;
    }

    /**
     * @see net.malta.model.PaymentMethodDao#create(java.lang.String, java.lang.String)
     */
    public net.malta.model.PaymentMethod create(
        java.lang.String name,
        java.lang.String note)
    {
        return (net.malta.model.PaymentMethod)this.create(TRANSFORM_NONE, name, note);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#create(int, java.lang.String, java.lang.String)
     */
    public java.lang.Object create(
        final int transform,
        java.lang.String name,
        java.lang.String note)
    {
        net.malta.model.PaymentMethod entity = new net.malta.model.PaymentMethodImpl();
        entity.setName(name);
        entity.setNote(note);
        return this.create(transform, entity);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#update(net.malta.model.PaymentMethod)
     */
    public void update(net.malta.model.PaymentMethod paymentMethod)
    {
        if (paymentMethod == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.update - 'paymentMethod' can not be null");
        }
        this.getHibernateTemplate().update(paymentMethod);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#update(java.util.Collection)
     */
    public void update(final java.util.Collection entities)
    {
        if (entities == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.update - 'entities' can not be null");
        }
        this.getHibernateTemplate().execute(
            new org.springframework.orm.hibernate3.HibernateCallback()
            {
                public java.lang.Object doInHibernate(org.hibernate.Session session)
                    throws org.hibernate.HibernateException
                {
                    for (java.util.Iterator entityIterator = entities.iterator(); entityIterator.hasNext();)
                    {
                        update((net.malta.model.PaymentMethod)entityIterator.next());
                    }
                    return null;
                }
            },
            true);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#remove(net.malta.model.PaymentMethod)
     */
    public void remove(net.malta.model.PaymentMethod paymentMethod)
    {
        if (paymentMethod == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.remove - 'paymentMethod' can not be null");
        }
        this.getHibernateTemplate().delete(paymentMethod);
    }

    /**
     * @see net.malta.model.PaymentMethodDao#remove(java.lang.Integer)
     */
    public void remove(java.lang.Integer id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.remove - 'id' can not be null");
        }
        net.malta.model.PaymentMethod entity = this.load(id);
        if (entity != null)
        {
            this.remove(entity);
        }
    }

    /**
     * @see net.malta.model.PaymentMethodDao#remove(java.util.Collection)
     */
    public void remove(java.util.Collection entities)
    {
        if (entities == null)
        {
            throw new IllegalArgumentException(
                "PaymentMethod.remove - 'entities' can not be null");
        }
        this.getHibernateTemplate().deleteAll(entities);
    }
    /**
     * Allows transformation of entities into value objects
     * (or something else for that matter), when the <code>transform</code>
     * flag is set to one of the constants defined in <code>net.malta.model.PaymentMethodDao</code>, please note
     * that the {@link #TRANSFORM_NONE} constant denotes no transformation, so the entity itself
     * will be returned.
     *
     * If the integer argument value is unknown {@link #TRANSFORM_NONE} is assumed.
     *
     * @param transform one of the constants declared in {@link net.malta.model.PaymentMethodDao}
     * @param entity an entity that was found
     * @return the transformed entity (i.e. new value object, etc)
     * @see #transformEntities(int,java.util.Collection)
     */
    protected java.lang.Object transformEntity(final int transform, final net.malta.model.PaymentMethod entity)
    {
        java.lang.Object target = null;
        if (entity != null)
        {
            switch (transform)
            {
                case TRANSFORM_NONE : // fall-through
                default:
                    target = entity;
            }
        }
        return target;
    }

    /**
     * Transforms a collection of entities using the
     * {@link #transformEntity(int,net.malta.model.PaymentMethod)}
     * method. This method does not instantiate a new collection.
     * <p/>
     * This method is to be used internally only.
     *
     * @param transform one of the constants declared in <code>net.malta.model.PaymentMethodDao</code>
     * @param entities the collection of entities to transform
     * @see #transformEntity(int,net.malta.model.PaymentMethod)
     */
    protected void transformEntities(final int transform, final java.util.Collection entities)
    {
        switch (transform)
        {
            case TRANSFORM_NONE : // fall-through
                default:
                // do nothing;
        }
    }

}