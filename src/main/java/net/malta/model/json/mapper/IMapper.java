/**
 * @author Suri
 * Mapper interface mapping output with the input - mapper pattern
 */
package net.malta.model.json.mapper;

public interface IMapper<I,O> {

	public void map(I i,O o);
}
