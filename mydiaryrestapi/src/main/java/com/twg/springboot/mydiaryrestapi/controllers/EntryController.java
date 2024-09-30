package com.twg.springboot.mydiaryrestapi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twg.springboot.mydiaryrestapi.entities.Entry;
import com.twg.springboot.mydiaryrestapi.service.EntryService;

@RestController
@RequestMapping("/entries")
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	
	@GetMapping("/")
	public List<Entry> findAllEntries()
	{
		List<Entry> entrieslist=entryService.findAll();
		
		return entrieslist;
	}
	
	
	@PostMapping("/")
	public Entry saveEntry(@RequestBody Entry entry)
	{
		Entry savedEntry=entryService.saveEntry(entry);
		
		return savedEntry;
	}
	
	
	@PutMapping("/")
	public Entry updateEntry(@RequestBody Entry entry)
	{
		Entry updatedEntry=entryService.updateEntry(entry);
		return updatedEntry;
	}
	
	@PutMapping("/{id}")
	public Entry updateEntry1(@PathVariable("id") int id,@RequestBody Entry entry)
	{
		Entry entry1=entryService.findById(id);//entry1 is from db
		
		entry1.setDescription(entry.getDescription());
		entry1.setEntrydate(entry.getEntrydate());
		entry1.setUserid(entry.getUserid());
		
		
		Entry updatedEntry=entryService.updateEntry(entry1);
		return updatedEntry;
	}
	
	@PatchMapping("/{id}")
	public Entry updateEntry2(@PathVariable("id") int id,@RequestBody Entry entry)
	{
		Entry entry1=entryService.findById(id);//entry1 is from db
		
		String desc=entry.getDescription();
		Date dt=entry.getEntrydate();
		long usrid=entry.getUserid();
		
		if(desc!=null && desc.length()>0)
			entry1.setDescription(entry.getDescription());
		
		if(dt!=null)
		    entry1.setEntrydate(entry.getEntrydate());
		
		if(usrid>0)
			entry1.setUserid(entry.getUserid());
		
		
		Entry updatedEntry=entryService.updateEntry(entry1);
		return updatedEntry;
	}
	

	
	
	@GetMapping("/{id}")
	public Entry getEntry(@PathVariable("id") int id)
	{
		Entry entry = entryService.findById(id);
		return entry;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEntry(@PathVariable("id") int id)
	{
		Entry entry = entryService.findById(id);
		entryService.deleteEntry(entry);
	}
	
	
	

}
