package com.example.notes.di

import android.app.Application
import androidx.room.Room
import com.example.notes.feature_notes.domain.use_case.*
import com.example.notes.feature_notes.data.local_data.data_source.NotesDatabase
import com.example.notes.feature_notes.data.local_data.repository.NotesRepositoryImpl
import com.example.notes.feature_notes.domain.repository.NoteRepository
import com.example.notes.feature_profile.domain.use_case.ValidateEmail
import com.example.notes.feature_profile.domain.use_case.ValidatePassword
import com.example.notes.feature_profile.domain.use_case.ValidateRePassword
import com.example.notes.feature_profile.domain.use_case.ValidateTerms
import com.example.notes.feature_profile.domain.use_case.ValidateUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesRepository(db: NotesDatabase): NoteRepository {
        return NotesRepositoryImpl(db.notesDao)
    }

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getAllNotesUseCase = GetAllNotesUseCase(repository),
            getNoteByIdUseCase = GetNoteByIdUseCase(repository),
            insertNoteUseCase = InsertNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideValidateUseCases(): ValidateUseCases {
        return ValidateUseCases(
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword(),
            validateRePassword = ValidateRePassword(),
            validateTerms = ValidateTerms()
        )
    }

}