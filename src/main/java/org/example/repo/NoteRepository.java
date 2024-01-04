package org.example.repo;

import org.example.dto.NoteDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface NoteRepository extends CrudRepository<NoteDetail,Integer> {

    List<NoteDetail> findAll();

    //@Query(value = "Select nd from NoteDetail nd where NoteDetailId = :id")
    NoteDetail findByNoteDetailId(Integer id);

    @Query(value = "Select nd from NoteDetail nd where nd.note like '%note%' ",nativeQuery = true)
    List<NoteDetail> getNotesByText(String note);

}
