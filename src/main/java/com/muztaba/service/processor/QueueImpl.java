package com.muztaba.service.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by seal on 8/20/16.
 */
@Service
public class QueueImpl<E> implements QueueService<E> {

    private static final Logger logger = LoggerFactory.getLogger(QueueImpl.class);

    private final BlockingDeque<E> queue = new LinkedBlockingDeque<>();

    @Override
    public int required() {
        logger.info("Queue size {}", queue.size());
        return 5;
    }

    @Override
    public void add(Collection<? extends E> elements) {
        logger.info("Element will added {}", elements.size());
        queue.addAll(elements);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public E get() {
        E e = queue.poll();
        return e;
    }

    @Override
    public List<E> getList(int size) {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < Math.min(size, queue.size()); i++) {
            list.add(queue.poll());
        }
        return list;
    }
}
