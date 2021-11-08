package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.Band;
import adeo.leroymerlin.cdp.Event;
import adeo.leroymerlin.cdp.Member;

import java.util.Collections;

abstract class EventControllerTest {

     Event event1(){
         Event event = new Event();
         event.setId(1L);
         event.setTitle("event1");
         event.setImgUrl("test");
         event.setBands(Collections.singleton(band1()));
         event.setNbStars(0);
         event.setComment("test");
         return event;
     }

     Band band1(){
         Band band = new Band();
         band.setMembers(Collections.singleton(member()));
         band.setName("Band 1");
         return band;
     }

     Member member(){
         Member member = new Member();
         member.setName("Julien");
         return member;

     }
}
