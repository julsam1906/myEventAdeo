package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.Band;
import adeo.leroymerlin.cdp.Event;
import adeo.leroymerlin.cdp.Member;

import java.util.Collections;

abstract class EventServiceTest {

    Event event1(){
        Event event = new Event();
        event.setId(1L);
        event.setTitle("event1");
        event.setImgUrl("test");
        event.setBands(Collections.singleton(band1()));
        event.setNbStars(3);
        event.setComment("test");
        return event;
    }

    Event event2(){
        Event event = new Event();
        event.setId(2L);
        event.setTitle("event2");
        event.setImgUrl("test");
        event.setBands(Collections.singleton(band2()));
        event.setNbStars(5);
        event.setComment("test event 2");
        return event;
    }

    Band band1(){
        Band band = new Band();
        band.setMembers(Collections.singleton(member1()));
        band.setName("Band1");
        return band;
    }

    Band band2(){
        Band band = new Band();
        band.setMembers(Collections.singleton(member2()));
        band.setName("Band2");
        return band;
    }

    Member member1(){
        Member member = new Member();
        member.setName("Julien");
        return member;
    }

    Member member2(){
        Member member = new Member();
        member.setName("Test non retourné");
        return member;
    }
}
