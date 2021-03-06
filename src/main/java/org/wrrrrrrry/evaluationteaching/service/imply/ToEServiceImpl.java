package org.wrrrrrrry.evaluationteaching.service.imply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wrrrrrrry.evaluationteaching.entity.*;
import org.wrrrrrrry.evaluationteaching.mapper.ToEMapper;
import org.wrrrrrrry.evaluationteaching.service.ToEService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ToEServiceImpl implements ToEService {
    @Autowired
    ToEMapper toEMapper;

    @Override
    public List<Questionnaire> queryFinishedQuestionnaire(String code, boolean isStudent) {
        if (isStudent) {
            return toEMapper.selectStudentFinishedQuestionnaire(code);
        } else {
            return toEMapper.selectTeacherFinishedQuestionnaire(code);
        }

    }

    @Override
    public List<Map<String, String>> queryFinishedDetailById(int id,boolean isStudent,String code) {
        if (isStudent){
            return toEMapper.selectStudentFinishedDetailById(id,code);
        }else {
            return toEMapper.selectTeacherFinishedDetailById(id,code);
        }
    }

    @Override
    public List<Questionnaire> queryStudentProgressingQuestionnaire(String code) {
        return toEMapper.selectStudentProgressingQuestionnaire(code);
    }

    @Override
    public List<Questionnaire> queryTeacherProgressingQuestionnaire(String code) {
        return toEMapper.selectTeacherProgressingQuestionnaire(code);
    }

    @Override
    public List<Question> queryStudentProgressingDetailById(int id) {
        return toEMapper.selectStudentProgressingDetailById(id);
    }

    @Override
    public List<Question> queryTeacherProgressingDetailById(int id) {
        return toEMapper.selectTeacherProgressingDetailById(id);
    }

    @Override
    public int insertStudentAnswer(List<StudentAnswer> studentAnswers,String code) {
        int r = toEMapper.insertStudentAnswer(studentAnswers);
        toEMapper.updateStudentMessage(code,studentAnswers.get(0).getSttId());
        return r;
    }

    @Override
    public int insertTeacherAnswer(List<TeacherAnswer> teacherAnswers) {
        int r = toEMapper.insertTeacherAnswer(teacherAnswers);
        toEMapper.updateTeacherMessage(teacherAnswers.get(0).getValuatorId(),teacherAnswers.get(0).getTttId(),teacherAnswers.get(0).getBeValuatorId());
        return r;
    }

    @Override
    public Map bEInDe(int tttId) {
        Map result = new HashMap();
        result.put("answers",toEMapper.selectBEInDetail(tttId));
        return result;
    }

    @Override
    public List<Map> bEInPro(int tttId, String code) {
        return toEMapper.selectbEInPro(tttId,code);
    }

    @Override
    public List<Map> getBeEvaluationCourseList(String code) {
        return toEMapper.selectEvaluationCourseList(code);
    }

    @Override
    public Map bECoDe(int sttId) {
        Map result = new HashMap();
        result.put("answers",toEMapper.selectBECoDetail(sttId));
        return result;
    }

    @Override
    public List<Map> bECoPro(int sttId) {

        return toEMapper.selectbECoPro(sttId);
    }

    @Override
    public List<Map> getStudentEvaluationList() {
        return toEMapper.selectStudentEvaluationList();
    }

    @Override
    public List<Map> getTeacherEvaluationList() {
        return toEMapper.selectTeacherEvaluationList();
    }

    @Override
    public List<Map> aeTD(int tttId) {
        return toEMapper.selectAETD(tttId);
    }

    @Override
    public List<Map> aeTP(int tttId) {
        return toEMapper.selectAETP(tttId);
    }

    @Override
    public List<Map> aeSD(int sttId) {
        return toEMapper.selectAESD(sttId);
    }

    @Override
    public List<Map> aeSP(int sttId) {
        return toEMapper.selectAESP(sttId);
    }

    @Override
    public List<Map> getBeEvaluationInstituteList(String code) {
        return toEMapper.selectEvaluationInstituteList(code);
    }



}
