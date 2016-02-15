/**
 * @author Suri
 * Mapper interface mapping output with the input - mapper pattern
 */
package net.malta.mapper;

public interface IMapper<I,O> {

	public O map(I i,O o);
}
