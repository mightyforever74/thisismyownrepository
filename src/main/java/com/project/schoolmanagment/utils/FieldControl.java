package com.project.schoolmanagment.utils;

import com.project.schoolmanagment.exception.ConflictException;
import com.project.schoolmanagment.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FieldControl {

    private final AdminRepository adminRepository;
    private final DeanRepository deanRepository;
    private final ViceDeanRepository viceDeanRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GuestUserRepository guestUserRepository;

    //admin ,vicedean, student, teacher,guestuser should have unique
    //username , email, , ssn and phonenumber

    public void checkDuplicate(String... values) {
        String username = values[0];
        String ssn = values[1];
        String phone = values[2];
        String email = "";
        if (values.length == 4) {
            email = values[3];
        }
        if (adminRepository.existsByUsername(username) ||
                deanRepository.existsByUsername(username) ||
                studentRepository.existsByUsername(username) ||
                teacherRepository.existsByUsername(username) ||
                viceDeanRepository.existsByUsername(username) ||
                guestUserRepository.existsByUsername(username)) {
            throw new ConflictException(String.format(Messages.ALREADY_REGISTER_MESSAGE_USERNAME, username));
        } else if (adminRepository.existsBySsn(ssn) ||
                deanRepository.existsBySsn(ssn) ||
                studentRepository.existsBySsn(ssn) ||
                teacherRepository.existsBySsn(ssn) ||
                viceDeanRepository.existsBySsn(ssn) ||
                guestUserRepository.existsBySsn(ssn)) {
            throw new ConflictException(String.format(Messages.ALREADY_REGISTER_MESSAGE_SSN, ssn));

        } else if (adminRepository.existsByPhoneNumber(phone) ||
                deanRepository.existsByPhoneNumber(phone) ||
                studentRepository.existsByPhoneNumber(phone) ||
                teacherRepository.existsByPhoneNumber(phone) ||
                viceDeanRepository.existsByPhoneNumber(phone) ||
                guestUserRepository.existsByPhoneNumber(phone)) {
            throw new ConflictException(String.format(Messages.ALREADY_REGISTER_MESSAGE_PHONE_NUMBER, phone));
        } else if (studentRepository.existsByEmail(email) ||
                teacherRepository.existsByEmail(email))
            throw new ConflictException(String.format(Messages.ALREADY_REGISTER_MESSAGE_EMAIL , email));
    }
}

