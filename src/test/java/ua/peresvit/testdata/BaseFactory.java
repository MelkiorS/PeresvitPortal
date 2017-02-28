package ua.peresvit.testdata;

import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public interface BaseFactory<T> {

    public T getFirst();
    public T getSecond();
    public T getNew();
    public List<T> getAll();

}
