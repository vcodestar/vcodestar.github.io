window.addEventListener('beforeunload', () => {
    speechSynthesis.cancel();
});

const texts = [
    `<h1 class="headerTitle">Who am I?</h1>
    <p id="iam">
        I am a senior undergraduate student in the Department of Computer Science and Engineering at the University of Ioannina, maintaining a GPA of 7.61/10 and currently just one class away from graduation. My primary interests lie in systems programming, application development, and artificial intelligence, where I have gained a strong blend of theoretical knowledge and hands-on experience. Throughout my studies and personal projects, I’ve worked to develop skills in software system design, machine learning applications, and creating innovative solutions to computational challenges.
    </p>
    `,
    `<h1 class="headerTitle">Experience</h1>
    <ul>
        <li>
            <strong>Internship at Terracom SA</strong>
            <p>
                During my internship at Terracom SA, I focused on two main areas: automation testing and web development. I worked with <strong>Cypress</strong> for end-to-end testing, creating automated test scripts to ensure the reliability and efficiency of the software. This involved writing, executing, and debugging tests to validate various user interactions and flows within the applications.
            </p>
            <p>
                In addition to testing, I contributed to <strong>web development</strong> projects using <strong>Angular</strong>. My responsibilities included developing dynamic and responsive user interfaces, implementing front-end components, and collaborating with team members to enhance application functionality. This experience strengthened my skills in JavaScript, TypeScript, and modern front-end frameworks.
            </p>
        </li>
    </ul>
    `,
];

const textsToAudio = [
    "Who am I? I am a senior undergraduate student in the Department of Computer Science and Engineering at the University of Ioannina, maintaining a GPA of 7.61/10 and currently just one class away from graduation. My primary interests lie in systems programming, application development, and artificial intelligence, where I have gained a strong blend of theoretical knowledge and hands-on experience. Throughout my studies and personal projects, I’ve worked to develop skills in software system design, machine learning applications, and creating innovative solutions to computational challenges.",
    "Experience. Internship at Terracom SA. During my internship at Terracom SA, I focused on two main areas: automation testing and web development. I worked with Cypress for end-to-end testing, creating automated test scripts to ensure the reliability and efficiency of the software. This involved writing, executing, and debugging tests to validate various user interactions and flows within the applications. In addition to testing, I contributed to web development projects using Angular. My responsibilities included developing dynamic and responsive user interfaces, implementing front-end components, and collaborating with team members to enhance application functionality. This experience strengthened my skills in JavaScript, TypeScript, and modern front-end frameworks"
];

let currentIndex = -1;
let utterance = new SpeechSynthesisUtterance();
const btns = document.getElementById('btns');

function updateText() {
    speechSynthesis.cancel();
    document.getElementById('text-content').innerHTML = texts[currentIndex];
}

function nextText() {
    btns.style.visibility = 'visible';
    if (currentIndex < texts.length - 1) {
        currentIndex++;
        updateText();
    }
}

function prevText() {
    if (currentIndex > 0) {
        currentIndex--;
        updateText();
    }
}

function speak() {
    speechSynthesis.cancel();

    const text = textsToAudio[currentIndex];
    
    utterance.text = text;
    utterance.lang = 'en-US'; 
    utterance.pitch = 1;      
    utterance.rate = 1;       
    utterance.volume = 1;     

    speechSynthesis.speak(utterance);
}

function pause() {
    speechSynthesis.cancel();
}

function restart() {
    speechSynthesis.cancel();
    const text = textsToAudio[currentIndex];
    utterance.text = text;
    speechSynthesis.speak(utterance);
}