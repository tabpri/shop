// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: HibernateEntity.vsl in andromda-hibernate-cartridge.
//
package net.malta.model;

/**
 * 
 */
public abstract class Choise
    implements java.io.Serializable
    
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -886770031888382102L;
    
    
    private int wp_posts_id = 0;
    private String name = "";
    private String img = "";
    private int carriage = 0;
    private String varietychoise = "";
    
    
    public String getVarietychoise() {
		return varietychoise;
	}

	public void setVarietychoise(String varietychoise) {
		this.varietychoise = varietychoise;
	}

	public int getCarriage() {
		return carriage;
	}

	public void setCarriage(int carriage) {
		this.carriage = carriage;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

    private int pricewithtax;

    /**
     * 
     */
    public int getPricewithtax()
    {
        return this.pricewithtax;
    }

    public void setPricewithtax(int pricewithtax)
    {
        this.pricewithtax = pricewithtax;
    }

    private boolean wrapping;

    /**
     * 
     */
    public boolean isWrapping()
    {
        return this.wrapping;
    }

    public void setWrapping(boolean wrapping)
    {
        this.wrapping = wrapping;
    }


	public int getWp_posts_id() {
		return wp_posts_id;
	}

	public void setWp_posts_id(int wp_posts_id) {
		this.wp_posts_id = wp_posts_id;
	}
    
    
    
    private net.malta.model.Purchase purchase;

    /**
     * 
     */
    public net.malta.model.Purchase getPurchase()
    {
        return this.purchase;
    }

    public void setPurchase(net.malta.model.Purchase purchase)
    {
        this.purchase = purchase;
    }

    private net.malta.model.Item item;

    /**
     * 
     */
    public net.malta.model.Item getItem()
    {
        return this.item;
    }

    public void setItem(net.malta.model.Item item)
    {
        this.item = item;
    }

    private java.util.Collection deliveryAddressChoises = new java.util.HashSet();

    /**
     * 
     */
    public java.util.Collection getDeliveryAddressChoises()
    {
        return this.deliveryAddressChoises;
    }

    public void setDeliveryAddressChoises(java.util.Collection deliveryAddressChoises)
    {
        this.deliveryAddressChoises = deliveryAddressChoises;
    }

    /**
     * Returns <code>true</code> if the argument is an Choise instance and all identifiers for this entity
     * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
     */
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Choise))
        {
            return false;
        }
        final Choise that = (Choise)object;
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
     * Constructs new instances of {@link net.malta.model.Choise}.
     */
    public static final class Factory
    {
        /**
         * Constructs a new instance of {@link net.malta.model.Choise}.
         */
        public static net.malta.model.Choise newInstance()
        {
            return new net.malta.model.ChoiseImpl();
        }

        /**
         * Constructs a new instance of {@link net.malta.model.Choise}, taking all required and/or
         * read-only properties as arguments.
         */
        public static net.malta.model.Choise newInstance(int ordernum, int pricewithtax, boolean wrapping, net.malta.model.Purchase purchase, net.malta.model.Item item)
        {
            final net.malta.model.Choise entity = new net.malta.model.ChoiseImpl();
            entity.setOrdernum(ordernum);
            entity.setPricewithtax(pricewithtax);
            entity.setWrapping(wrapping);
            entity.setPurchase(purchase);
            entity.setItem(item);
            return entity;
        }

        /**
         * Constructs a new instance of {@link net.malta.model.Choise}, taking all possible properties
         * (except the identifier(s))as arguments.
         */
        public static net.malta.model.Choise newInstance(int ordernum, int pricewithtax, boolean wrapping, net.malta.model.Purchase purchase, net.malta.model.Item item, java.util.Collection deliveryAddressChoises)
        {
            final net.malta.model.Choise entity = new net.malta.model.ChoiseImpl();
            entity.setOrdernum(ordernum);
            entity.setPricewithtax(pricewithtax);
            entity.setWrapping(wrapping);
            entity.setPurchase(purchase);
            entity.setItem(item);
            entity.setDeliveryAddressChoises(deliveryAddressChoises);
            return entity;
        }
    }

// HibernateEntity.vsl merge-point
}