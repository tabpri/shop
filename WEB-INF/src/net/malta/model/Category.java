// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: HibernateEntity.vsl in andromda-hibernate-cartridge.
//
package net.malta.model;

/**
 * 
 */
public abstract class Category
    implements java.io.Serializable
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 7546462523555310555L;

    private java.lang.Integer id;

    /**
     * 
     */
    public java.lang.Integer getId()
    {
        return this.id;
    }

    public void setId(java.lang.Integer id)
    {
        this.id = id;
    }

    private java.lang.String name;

    /**
     * 
     */
    public java.lang.String getName()
    {
        return this.name;
    }

    public void setName(java.lang.String name)
    {
        this.name = name;
    }

    private java.util.Collection products = new java.util.HashSet();

    /**
     * 
     */
    public java.util.Collection getProducts()
    {
        return this.products;
    }

    public void setProducts(java.util.Collection products)
    {
        this.products = products;
    }

    /**
     * Returns <code>true</code> if the argument is an Category instance and all identifiers for this entity
     * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
     */
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Category))
        {
            return false;
        }
        final Category that = (Category)object;
        if (this.id == null || that.getId() == null || !this.id.equals(that.getId()))
        {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code based on this entity's identifiers.
     */
    public int hashCode()
    {
        int hashCode = 0;
        hashCode = 29 * hashCode + (id == null ? 0 : id.hashCode());

        return hashCode;
    }

    /**
     * Constructs new instances of {@link net.malta.model.Category}.
     */
    public static final class Factory
    {
        /**
         * Constructs a new instance of {@link net.malta.model.Category}.
         */
        public static net.malta.model.Category newInstance()
        {
            return new net.malta.model.CategoryImpl();
        }

        /**
         * Constructs a new instance of {@link net.malta.model.Category}, taking all required and/or
         * read-only properties as arguments.
         */
        public static net.malta.model.Category newInstance(java.lang.String name)
        {
            final net.malta.model.Category entity = new net.malta.model.CategoryImpl();
            entity.setName(name);
            return entity;
        }

        /**
         * Constructs a new instance of {@link net.malta.model.Category}, taking all possible properties
         * (except the identifier(s))as arguments.
         */
        public static net.malta.model.Category newInstance(java.lang.String name, java.util.Collection products)
        {
            final net.malta.model.Category entity = new net.malta.model.CategoryImpl();
            entity.setName(name);
            entity.setProducts(products);
            return entity;
        }
    }
    
// HibernateEntity.vsl merge-point
}