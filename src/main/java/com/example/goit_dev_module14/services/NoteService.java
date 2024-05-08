package com.example.goit_dev_module14.services;

import com.example.goit_dev_module14.dtos.NoteDto;
import com.example.goit_dev_module14.entities.Note;
import com.example.goit_dev_module14.repositories.NoteRepository;
import com.example.goit_dev_module14.utils.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteDto> getAllNotes(){
        List<Note> notes = noteRepository.listAll();

        return NoteMapper.toNoteDtoList(notes);
    }

    public NoteDto getNoteById(long id) throws Exception{
        return NoteMapper.toNoteDto(noteRepository.getById(id));
    }

    public NoteDto createNote(NoteDto noteDto){
        Random random = new Random();

        noteDto.setId(random.nextLong(100) + 1);

        return NoteMapper.toNoteDto(noteRepository.add(NoteMapper.toNoteEntity(noteDto)));
    }

    public void deleteNote(long id) throws Exception{
        noteRepository.deleteById(id);
    }

    public void updateNote(NoteDto noteDto) throws Exception{
        noteRepository.update(NoteMapper.toNoteEntity(noteDto));
    }
}
