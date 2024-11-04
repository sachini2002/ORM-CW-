package lk.ijse.bo.impl;

import lk.ijse.bo.RegistrationBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.RegistrationDao;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.entity.Courses;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Registration;
import lk.ijse.entity.Student;


import java.util.ArrayList;
import java.util.List;

public class RegistrationBoImpl implements RegistrationBo {

    RegistrationDao registrationDao = (RegistrationDao) DaoFactory.getInstance().getDao(DaoType.Registration);
    @Override
    public boolean saveRegistration(RegistrationDTO registrationDTO) {
       return registrationDao.save(new Registration(registrationDTO.getRegistrationId(),registrationDTO.getAdvanced(),registrationDTO.getDate(),registrationDTO.getCourses(),registrationDTO.getStudent(),registrationDTO.getPayment()));
    }

    @Override
    public List<Courses> getCourseId() {
        List<Courses> ids = registrationDao.getIds();
        return ids;
    }

    @Override
    public List<Student> getStudentId() {
       return registrationDao.getStudentIds();
    }

    @Override
    public RegistrationDTO searchRegistrations(String id) {
        try {
            Registration registration = registrationDao.find(id);

            RegistrationDTO registrationDTO = new RegistrationDTO(registration.getRegistrationId(), registration.getAdvanced(), registration.getDate(), registration.getCourses(), registration.getStudent(),registration.getPayment());
            return registrationDTO;
        }catch (Exception e){
            return null;
        }



    }

    @Override
    public boolean deleteRegistration(String id) {
     return registrationDao.delete(id);
    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) {
      return registrationDao.update(new Registration(registrationDTO.getRegistrationId(),registrationDTO.getAdvanced(),registrationDTO.getDate(),registrationDTO.getCourses(),registrationDTO.getStudent(),registrationDTO.getPayment()));
    }

    @Override
    public List<RegistrationDTO> loadTable() {
        List<Registration> registrations = registrationDao.loadTable();
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        for (Registration registration : registrations) {
            RegistrationDTO registrationDTO = new RegistrationDTO(registration.getRegistrationId(), registration.getAdvanced(), registration.getDate(), registration.getCourses(), registration.getStudent(),registration.getPayment());
            registrationDTOS.add(registrationDTO);
        }
        return registrationDTOS;
    }

    @Override
    public List<Payment> getPaymentID() {
       return registrationDao.getpayId();
    }

    @Override
    public RegistrationDTO findRegistrationById(String regId) {
        Registration registrationById = registrationDao.findRegistrationById(regId);
        RegistrationDTO registrationDTO = new RegistrationDTO(registrationById.getRegistrationId(), registrationById.getAdvanced(), registrationById.getDate(), registrationById.getCourses(), registrationById.getStudent(), registrationById.getPayment());
        return registrationDTO;
    }
}
