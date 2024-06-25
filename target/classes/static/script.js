function dynamicInput(containerId, buttonId, max){
    const container = document.getElementById(containerId);
    const addButton = document.getElementById(buttonId);
    addButton.addEventListener("click",()=>{
        if (container.getElementsByTagName("div").length < max){
            const innerContainer = document.createElement("div");
            innerContainer.className = "inner-skill-container";
            const input = document.createElement("input");
            input.type = "text";
            innerContainer.appendChild(input);
            const deleteButton = document.createElement("button");
            deleteButton.className = "delete-button";
            deleteButton.textContent = "×";
            deleteButton.addEventListener("click",()=>{
                container.removeChild(innerContainer);
            })
            innerContainer.appendChild(deleteButton);
            container.appendChild(innerContainer);
        }
        else{
            alert("Maximum limit reached!");
        }
    })
}
function dynamicDiv(){
    const experienceContainer = document.getElementById("experience-container");
    const addExperienceButton = document.getElementById("add-experience");
    const projectContainer = document.getElementById("project-container");
    const addProjectButton = document.getElementById("add-project");
    addExperienceButton.addEventListener("click",()=>{
        if (experienceContainer.getElementsByTagName("div").length + projectContainer.getElementsByTagName("div").length < 4){
            const innerContainer = document.createElement("div");
            innerContainer.className = "inner-container";

            const companyName = document.createElement("input");
            const companyNameLabel = document.createElement("label");
            companyNameLabel.textContent = "Company Name";
            companyNameLabel.className = "inner-title";
            companyName.className = "company-name";
            companyName.type = "text";
            companyNameLabel.append(companyName);

            const durationStart = document.createElement("input");
            const durationStartLabel = document.createElement("label");
            durationStartLabel.textContent = "Started";
            durationStartLabel.className = "inner-title";
            durationStart.className = "duration-start";
            durationStart.type = "text";
            durationStartLabel.append(durationStart);

            const durationEnd = document.createElement("input");
            const durationEndLabel = document.createElement("label");
            durationEndLabel.className = "inner-title";
            durationEndLabel.textContent = "Ended"
            durationEnd.className = "duration-end";
            durationEnd.type = "text";
            durationEndLabel.append(durationEnd);

            const jobRole = document.createElement("input");
            const jobRoleLabel = document.createElement("label");
            jobRoleLabel.className = "inner-title";
            jobRoleLabel.textContent = "Job Role";
            jobRole.className = "job-role"
            jobRole.type = "text";
            jobRoleLabel.append(jobRole);

            const location = document.createElement("input");
            const locationLabel = document.createElement("label");
            locationLabel.className = "inner-title";
            locationLabel.textContent = "Location";
            location.className = "location";
            location.type = "text";
            locationLabel.append(location);

            const responsibilityLabel = document.createElement("label");
            responsibilityLabel.textContent = "Responsibilities";
            responsibilityLabel.className = "inner-title";
            const responsibility1 = document.createElement("input");
            responsibility1.className = "responsibility";
            responsibility1.type = "text";
            responsibility1.maxLength = 90;
            const responsibility2 = document.createElement("input");
            responsibility2.className = "responsibility";
            responsibility2.type = "text";
            responsibility2.maxLength = 90;
            const responsibility3 = document.createElement("input");
            responsibility3.className = "responsibility";
            responsibility3.type = "text";
            responsibility3.maxLength = 90;
            const responsibility4 = document.createElement("input");
            responsibility4.className = "responsibility";
            responsibility4.type = "text";
            responsibility4.maxLength = 90;
            responsibilityLabel.append(responsibility1, responsibility2, responsibility3, responsibility4);

            const deleteButton = document.createElement("button");
            deleteButton.className = "delete-button";
            deleteButton.textContent = "×";
            deleteButton.addEventListener("click",()=>{
                experienceContainer.removeChild(innerContainer);
            })
            innerContainer.append(companyNameLabel, durationStartLabel, durationEndLabel, jobRoleLabel, locationLabel, responsibilityLabel);
            innerContainer.appendChild(deleteButton);
            experienceContainer.appendChild(innerContainer);
        }
        else{
            alert("Maximum limit reached!");
        }
    })
    addProjectButton.addEventListener("click",()=>{
            if (experienceContainer.getElementsByTagName("div").length + projectContainer.getElementsByTagName("div").length < 4){
                const innerContainer = document.createElement("div");
                innerContainer.className = "inner-container";

                const projectName = document.createElement("input");
                const projectNameLabel = document.createElement("label");
                projectNameLabel.textContent = "Project Name";
                projectNameLabel.className = "inner-title";
                projectName.className = "project-name";
                projectName.type = "text";
                projectNameLabel.append(projectName);

                const durationStart = document.createElement("input");
                const durationStartLabel = document.createElement("label");
                durationStartLabel.textContent = "Started";
                durationStartLabel.className = "inner-title";
                durationStart.className = "duration-start";
                durationStart.type = "text";
                durationStartLabel.append(durationStart);

                const durationEnd = document.createElement("input");
                const durationEndLabel = document.createElement("label");
                durationEndLabel.textContent = "Ended";
                durationEndLabel.className = "inner-title";
                durationEnd.className = "duration-end";
                durationEnd.type = "text";
                durationEndLabel.append(durationEnd);

                const responsibilityLabel = document.createElement("label");
                responsibilityLabel.textContent = "Responsibilities";
                responsibilityLabel.className = "inner-title";
                const responsibility1 = document.createElement("input");
                responsibility1.className = "responsibility";
                responsibility1.type = "text";
                responsibility1.maxLength = 90;
                const responsibility2 = document.createElement("input");
                responsibility2.className = "responsibility";
                responsibility2.type = "text";
                responsibility2.maxLength = 90;
                const responsibility3 = document.createElement("input");
                responsibility3.className = "responsibility";
                responsibility3.type = "text";
                responsibility3.maxLength = 90;
                const responsibility4 = document.createElement("input");
                responsibility4.className = "responsibility";
                responsibility4.type = "text";
                responsibility4.maxLength = 90;
                responsibilityLabel.append(responsibility1, responsibility2, responsibility3, responsibility4);
                const deleteButton = document.createElement("button");
                deleteButton.className = "delete-button";
                deleteButton.textContent = "×";
                deleteButton.addEventListener("click",()=>{
                    projectContainer.removeChild(innerContainer);
                })
                innerContainer.append(projectNameLabel, durationStartLabel, durationEndLabel, responsibilityLabel);
                innerContainer.appendChild(deleteButton);
                projectContainer.appendChild(innerContainer);
            }
            else{
                alert("Maximum limit reached!");
            }
        }
    )}
function createPersonalObject(){
    const object = {};
    const container = document.getElementById("personal-container");
    elements = container.getElementsByTagName("input");
    for (let i = 0; i < elements.length; i++){
        let key = elements.item(i).id.replace("-","_");
        object[key] = elements.item(i).value;
    }
    return object;
}
function createObject(containerId){
    const array = [];
    const container = document.getElementById(containerId);
    const elements = container.getElementsByTagName("div");
    for (let i = 0; i < elements.length; i++){
        let input = elements.item(i).getElementsByTagName("input");
        let object = {};
        let responsibility = [];
        for (let j = 0; j < input.length; j++){
            let key = input.item(j).className.replace("-","_");
            if (key === "responsibility"){
                responsibility.push(input.item(j).value);
            }
            else{
                object[key] = input.item(j).value;
            }
        }
        if (containerId !== "education-container"){
            object["responsibility"] = responsibility;}
        array.push(object);
    }
    return array;
}
function createSkillObject(containerId){
    const array = [];
    const container = document.getElementById(containerId);
    const elements = container.getElementsByTagName("input");
    for (let i = 0; i < elements.length; i++){
        if (String(elements.item(i).value).length !== 0){
            array.push(elements.item(i).value);
        }
    }
    return array;
}
function generateRandomString(length) {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let result = '';
    const charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
}
function submit(buttonId){
    document.getElementById(buttonId).addEventListener("click",()=>{
        const request = {}
        const skill = {};
        skill["technical_skill"] = createSkillObject("technical-skill-container");
        skill["soft_skill"] = createSkillObject("soft-skill-container");
        skill["interest"] = createSkillObject("interest-container");
        request["personal_detail"] = createPersonalObject();
        request["education"] = createObject("education-container");
        request["experience"] = createObject("experience-container");
        request["project"] = createObject("project-container");
        request["skill"] = skill;
        let random_string = generateRandomString(15);
        request["random_string"] = random_string;
        fetch('http://localhost:3004/post-data', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(request)
        })
            .then(response => response.json().then(data => ({status: response.status, body: data})))
            .then(response => {
                if (response.status === 200) {
                    window.location.href = `http://localhost:3004/download/${random_string}`;
                    console.log(response.body);
                }
                else{
                    console.log(response.body);
                }
            })
    })
}
document.addEventListener("DOMContentLoaded",()=>{
    dynamicDiv();
    dynamicInput("technical-skill-container", "add-technical-skill",9,1);
    dynamicInput("soft-skill-container", "add-soft-skill",7,1);
    dynamicInput("interest-container","add-interest",5,1);
    submit("submit");
})