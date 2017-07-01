/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.acme.internal;

import com.acme.HelloWorld;
import org.xwiki.component.annotation.Component;

import javax.inject.Singleton;
import java.text.ParseException;
import java.util.List;

/**
 * Implementation of a <tt>HelloWorld</tt> component.
 */
@Component
@Singleton
public class DefaultHelloWorld implements HelloWorld
{

    Cal cal;

    public DefaultHelloWorld() {
        cal = new Cal();
    }

    @Override
    public String sayHello() {
        return "Hello";
    }

    @Override
    public void addEvent(Event event){
        cal.addEvent(event);
    }

    public void editEvent(String name, String date, String time, Event newEvent){
        cal.editEvent(name, date, time, newEvent);
    }

    @Override
    public String[] month() {
        try {
            return cal.displayMonth();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] s = new String[1];
        s[0] = "Not found";
        return s;
    }

    public List<String> getEventsForToday(String eventDate){
        return cal.getEventsForToday(eventDate);
    }

    @Override
    public void deleteEvent(String name, String date, String time) {
        cal.deleteEvent(name, date, time);
    }

    public int getNumberOfEventsForToday(String eventDate){
        return cal.getNumberOfEventsForToday(eventDate);
    }
}

