package com.github.xenlon.onlineresumebuilder;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResumeService {
    public String getDuration(String durationStart, String durationEnd){
        return durationStart +" - "+ durationEnd;
    }
    public ResponseEntity<String> createPDF(Resume resume) throws IOException, InterruptedException {
        int trigger = 0;
        String intro = """
                    \\documentclass[11pt]{article}
                    \\usepackage{graphicx}
                    \\setlength{\\parindent}{0pt}
                    \\usepackage{hyperref}
                    \\usepackage{enumitem}
                    \\usepackage[utf8]{inputenc}\s
                    \\usepackage[T1]{fontenc}
                    \\usepackage[brazil]{babel}
                    \\usepackage{lipsum}
                    \\usepackage[left=1.06cm,top=1.7cm,right=1.06cm,bottom=0.49cm]{geometry}
                                        
                    \\begin{document}
                """;
        String detail = """
                \\begin{center}
                    \\huge{\\textbf{%s}}\\\\\s
                \\end{center}
                \\begin{center}
                    %s \\textbullet \\ %s \\textbullet \\ %s \\textbullet \\ %s
                \\end{center}
                \\vspace{-10pt}
                \\hrulefill
                \\vspace{-2.5pt}
                """;
        String educationHeader = """
                \\begin{center}
                    \\Large{\\textbf{Education}}
                \\end{center}
                """;
        String education = """
                \\textbf{%s} \\hfill %s
                                    
                %s, Percentage: %s\\%% \\hfill
                                    
                \\vspace{10pt}
                
                """;
        String experienceHeader = """
                \\begin{center}
                    \\Large{\\textbf{Experience}}
                \\end{center}
                """;
        String experience = """
                \\textbf{%s} \\hfill %s
                                        
                \\textbf{%s} \\hfill %s
                \\begin{itemize}[noitemsep, topsep=0pt, partopsep=0pt, parsep=0pt]
                    \\item %s
                    \\item %s
                    \\item %s
                    \\item %s
                \\end{itemize}
                                            
                \\vspace{10pt}
                """;
        String projectHeader =
                """
                \\begin{center}
                    \\Large{\\textbf{Personal Projects}}
                \\end{center}
                """;
        String project = """
                \\textbf{%s}	\\hfill %s
                                    
                \\begin{itemize}[noitemsep, topsep=0pt, partopsep=0pt, parsep=0pt]
                    \\item %s
                    \\item %s
                    \\item %s
                    \\item %s
                \\end{itemize}
                                    
                \\vspace{10pt}
                
                """;
        String skill = """
                \\begin{center}
                    \\Large{\\textbf{Skills \\& Interests}}
                \\end{center}
                                    
                \\textbf{Technical skills:} %s
                                    
                \\textbf{Soft skills:} %s
                                    
                \\textbf{Interests:} %s
                                    
                """;
        StringBuilder detailPlaceholder = new StringBuilder();
        StringBuilder educationPlaceholder = new StringBuilder();
        StringBuilder experiencePlaceholder = new StringBuilder();
        StringBuilder projectPlaceholder = new StringBuilder();
        StringBuilder skillPlaceholder = new StringBuilder();
        String fileName = resume.getRandomString();
        Resume.PersonalDetail personal = resume.getPersonalDetail();
        if (!personal.getFullName().isEmpty() && !personal.getEmailAddress().isEmpty() && !personal.getGithubURL().isEmpty() && !personal.getLinkedinURL().isEmpty() && !personal.getPhoneNumber().isEmpty()) {
            trigger++;
            detailPlaceholder = new StringBuilder(String.format(detail, personal.getFullName(), personal.getPhoneNumber(), personal.getEmailAddress(),
                    personal.getGithubURL(), personal.getLinkedinURL()));
        }
        for (int i = 0; i < resume.getEducation().size(); i++){
            Resume.Education temp = resume.getEducation().get(i);
            if (i == 0 && !temp.getCollegeName().isEmpty()){
                trigger++;
                educationPlaceholder.append(educationHeader);
            }
            if (!temp.getCollegeName().isEmpty() && !temp.getPercentage().isEmpty()) {
                educationPlaceholder.append(String.format(education, temp.getCollegeName(), getDuration(temp.getDurationStart(),
                        temp.getDurationEnd()), temp.getDegreeName(), temp.getPercentage()));
            }
        }
        if (resume.getExperience().size() > 0) {
            trigger++;
            experiencePlaceholder.append(experienceHeader);
            for (int i = 0; i < resume.getExperience().size(); i++) {
                Resume.Experience temp = resume.getExperience().get(i);
                experiencePlaceholder.append(String.format(experience, temp.getCompanyName(),
                        getDuration(temp.getDurationStart(), temp.getDurationEnd()),  temp.getLocation(),
                        temp.getJobRole(), temp.getResponsibility().get(0), temp.getResponsibility().get(1),
                        temp.getResponsibility().get(2), resume.getExperience().get(i).getResponsibility().get(3)));
            }
        }
        if (resume.getProject().size() > 0) {
            trigger++;
            projectPlaceholder.append(projectHeader);
            for (int i = 0; i < resume.getProject().size(); i++) {
                Resume.Project temp = resume.getProject().get(i);
                projectPlaceholder.append(String.format(project, temp.getProjectName(),
                        getDuration(temp.getDurationStart(), temp.getDurationEnd()), temp.getResponsibility().get(0),
                        temp.getResponsibility().get(1), temp.getResponsibility().get(2), temp.getResponsibility().get(3)));
            }
        }
        Resume.Skill temp = resume.getSkill();
        if (!temp.getTechnicalSkill().isEmpty() && !temp.getSoftSkill().isEmpty() && !temp.getInterest().isEmpty()) {
            trigger++;
            skillPlaceholder.append(String.format(skill, temp.toString(temp.getTechnicalSkill()), temp.toString(temp.getSoftSkill()),
                    temp.toString(temp.getInterest())));
        }

        if (trigger >= 4) {
            FileWriter writer = new FileWriter("src/main/resources/resume/"+fileName+".tex");
            writer.write(intro);
            writer.write(String.valueOf(detailPlaceholder));
            writer.write(educationPlaceholder.toString());
            if (experiencePlaceholder.length() > 0){
                writer.write(experiencePlaceholder.toString());
            }
            if (projectPlaceholder.length() > 0){
                writer.write(projectPlaceholder.toString());
            }
            writer.write(String.valueOf(skillPlaceholder));
            writer.write("\\end{document}");
            writer.close();
            ProcessBuilder generatePDF = null;
            if (System.getProperty("os.name").contains("Windows")) {
                generatePDF = new ProcessBuilder("cmd.exe", "/c",
                        String.format("cd src/main/resources/resume && pdfLatex %s.tex && del /f %s.aux && del /f %s.out && del /f %s.log && del /f %s.tex",
                                fileName, fileName, fileName, fileName, fileName));
            } else if (System.getProperty("os.name").contains("Linux") || System.getProperty("os.name").contains("mac")) {
                generatePDF = new ProcessBuilder("/bin/bash", "-c",
                        String.format("cd src/main/resources/resume && pdfLatex %s.tex && rm -rf %s.aux && rm -rf %s.out && rm -rf %s.log && rm -rf %s.tex",
                                fileName, fileName, fileName, fileName, fileName));
            }
            generatePDF.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            generatePDF.redirectError(ProcessBuilder.Redirect.DISCARD);
            Process process = generatePDF.start();
            process.waitFor();
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType("application/json")).body("{\"message\":\"Success\"}");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.parseMediaType("application/json")).body("{\"message\":\"Failed\"}");
    }

    public ResponseEntity<Resource> sendPDF(String fileName) throws IOException {
        File file = new File("src/main/resources/resume/"+fileName+".pdf");
        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf");
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        }
        return null;
    }
    public void deletePDF(String fileName) throws InterruptedException, IOException {
        File file = new File("src/main/resources/resume/"+fileName+".pdf");
        if (file.exists()) {
            ProcessBuilder deletePDF = null;
            if (System.getProperty("os.name").contains("Windows")) {
                deletePDF = new ProcessBuilder("cmd.exe", "/c", "cd src/main/resources/resume && del /f " + fileName + ".pdf && del /f texput.log");
            } else if (System.getProperty("os.name").contains("Linux") || System.getProperty("os.name").contains("mac")) {
                deletePDF = new ProcessBuilder("/bin/bash", "-c", "cd src/main/resources/resume && rm -rf " + fileName + ".pdf && rm -rf texput.log");
            }
            deletePDF.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            deletePDF.redirectError(ProcessBuilder.Redirect.DISCARD);
            Process process = deletePDF.start();
            process.waitFor();
        }
    }
}

