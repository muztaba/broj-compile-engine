package com.muztaba.service.queue;

import java.util.Collection;
import java.util.List;

/**
 * Created by seal on 8/20/16.
 */
public interface QueueService<E> {

    int required();

    void add(Collection<? extends E> elements);

    E get();

    boolean isEmpty();

    List<E> getList(int size);

}
