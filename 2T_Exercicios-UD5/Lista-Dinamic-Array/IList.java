/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package memstore;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface IList<T> extends List<T> {
    public IList<T> process(Function<T,T> processor);
    public void update(Function<T,T> processor);
    public IList<T> filter(Predicate<T> filter);
}
