// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: HibernateEntity.vsl in andromda-hibernate-cartridge.
//
package net.malta.model;

/**
 * 
 */
public abstract class DeliveryAddressChoise
    implements java.io.Serializable
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -4732361222348013500L;

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

    private int ordernum;

    /**
     * 
     */
    public int getOrdernum()
    {
        return this.ordernum;
    }

    public void setOrdernum(int ordernum)
    {
        this.ordernum = ordernum;
    }

    private java.lang.String preferreddate;

    /**
     * 
     */
    public java.lang.String getPreferreddate()
    {
        return this.preferreddate;
    }

    public void setPreferreddate(java.lang.String preferreddate)
    {
        this.preferreddate = preferreddate;
    }

    private java.lang.String preferredtime;

    /**
     * 
     */
    public java.lang.String getPreferredtime()
    {
        return this.preferredtime;
    }

    public void setPreferredtime(java.lang.String preferredtime)
    {
        this.preferredtime = preferredtime;
    }

    private net.malta.model.DeliveryAddress deliveryAddress;

    /**
     * 
     */
    public net.malta.model.DeliveryAddress getDeliveryAddress()
    {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(net.malta.model.DeliveryAddress deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    private net.malta.model.Choise choise;

    /**
     * 
     */
    public net.malta.model.Choise getChoise()
    {
        return this.choise;
    }

    public void setChoise(net.malta.model.Choise choise)
    {
        this.choise = choise;
    }

    private net.malta.model.GiftCard giftCard;

    /**
     * 
     */
    public net.malta.model.GiftCard getGiftCard()
    {
        return this.giftCard;
    }

    public void setGiftCard(net.malta.model.GiftCard giftCard)
    {
        this.giftCard = giftCard;
    }

    /**
     * Returns <code>true</code> if the argument is an DeliveryAddressChoise instance and all identifiers for this entity
     * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
     */
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof DeliveryAddressChoise))
        {
            return false;
        }
        final DeliveryAddressChoise that = (DeliveryAddressChoise)object;
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
     * Constructs new instances of {@link net.malta.model.DeliveryAddressChoise}.
     */
    public static final class Factory
    {
        /**
         * Constructs a new instance of {@link net.malta.model.DeliveryAddressChoise}.
         */
        public static net.malta.model.DeliveryAddressChoise newInstance()
        {
            return new net.malta.model.DeliveryAddressChoiseImpl();
        }

        /**
         * Constructs a new instance of {@link net.malta.model.DeliveryAddressChoise}, taking all required and/or
         * read-only properties as arguments.
         */
        public static net.malta.model.DeliveryAddressChoise newInstance(int ordernum, java.lang.String preferreddate, java.lang.String preferredtime, net.malta.model.DeliveryAddress deliveryAddress, net.malta.model.Choise choise)
        {
            final net.malta.model.DeliveryAddressChoise entity = new net.malta.model.DeliveryAddressChoiseImpl();
            entity.setOrdernum(ordernum);
            entity.setPreferreddate(preferreddate);
            entity.setPreferredtime(preferredtime);
            entity.setDeliveryAddress(deliveryAddress);
            entity.setChoise(choise);
            return entity;
        }

        /**
         * Constructs a new instance of {@link net.malta.model.DeliveryAddressChoise}, taking all possible properties
         * (except the identifier(s))as arguments.
         */
        public static net.malta.model.DeliveryAddressChoise newInstance(int ordernum, java.lang.String preferreddate, java.lang.String preferredtime, net.malta.model.DeliveryAddress deliveryAddress, net.malta.model.Choise choise, net.malta.model.GiftCard giftCard)
        {
            final net.malta.model.DeliveryAddressChoise entity = new net.malta.model.DeliveryAddressChoiseImpl();
            entity.setOrdernum(ordernum);
            entity.setPreferreddate(preferreddate);
            entity.setPreferredtime(preferredtime);
            entity.setDeliveryAddress(deliveryAddress);
            entity.setChoise(choise);
            entity.setGiftCard(giftCard);
            return entity;
        }
    }
    
// HibernateEntity.vsl merge-point
}