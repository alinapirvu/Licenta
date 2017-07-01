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
package com.acme.script;

import com.acme.HelloWorld;
import com.acme.internal.DefaultHelloWorld;
import com.acme.internal.Event;
import org.xwiki.component.annotation.Component;
import org.xwiki.script.service.ScriptService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Calendar;
import java.util.List;

/**
 * Make the HelloWorld API available to scripting.
 */
@Component
@Named("hello")
@Singleton
public class HelloWorldScriptService implements ScriptService
{
    @Inject
    private HelloWorld helloWorld = new DefaultHelloWorld();

    public String greet()
    {
        return this.helloWorld.sayHello();
    }

    public void addEvent(String name, String date, String time, String place, String info, String invitedPeople){
        Event event = new Event(name, date, time, place, info, invitedPeople);
        //Event e = new Event("JavaEvent", "06-06-2017", "13:00", "home", "1mai", "me");
        this.helloWorld.addEvent(event);
        //this.helloWorld.addEvent(e);
    }

    public void editEvent(String name, String date, String time, Event newEvent){
        this.helloWorld.editEvent(name, date, time, newEvent);
    }

    public void deleteEvent(String name, String date, String time){
        this.helloWorld.deleteEvent(name, date, time);
    }

    public String[] getCal(){
        return this.helloWorld.month();
    }

    public List<String> getEventsForToday(String eventDate){
        return helloWorld.getEventsForToday(eventDate);
    }

    public int getNumberOfEventsForToday(String eventDate){
        return helloWorld.getNumberOfEventsForToday(eventDate);
    }

}
