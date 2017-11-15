/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sb.app.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sb.app.bo.Book;
import sb.app.bo.Order;
import sb.app.repo.BookRepository;
import sb.app.repo.OrderRepository;

@Component
public class OrderService {

    @Autowired
    private BookRepository books;

    @Autowired
    private OrderRepository orderRepo;

    private final Random amount = new Random();

    public Order constructOrder() {
        Book book = books.findOne(amount.nextInt(2) + 1);// create a reandom book
        Order order = new Order();
        order.setAmount(amount.nextInt(10) + 1);
        order.setBook(book);
        return order;
    }

    public void save(Order order) {
        orderRepo.save(order);

    }
}
