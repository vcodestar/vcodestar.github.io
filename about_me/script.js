// Handle browser tab close or page reload by canceling speech
window.addEventListener('beforeunload', () => {
    speechSynthesis.cancel();
});

const texts = [
    `<h1 class="headerTitle">Who am I?</h1>
    <p id="iam">
        I am a senior undergraduate student in the Department of Computer Science and Engineering at the University of Ioannina,  currently just one class away from graduation. My primary interests lie in systems programming, application development, and artificial intelligence, where I have gained a strong blend of theoretical knowledge and hands-on experience. Throughout my studies and personal projects, I’ve worked to develop skills in software system design, machine learning applications, and creating innovative solutions to computational challenges.
    </p>
    `,
    `<h1 class="headerTitle">Experience</h1>
    <ul>
        <li>
            <strong> Internship at Archimedes Unit, Athena Research Center </strong>
             <p>
                <br>I am currently engaged in an internship at the Archimedes Unit, Athena Research Center. More details about my work and contributions will be shared soon.
            </p><br><br><br>
        </li>
        <li>
            <strong>Internship at Terracom SA</strong><br>
            <p>
                <br>During my internship at Terracom SA, I focused on two main areas: automation testing and web development. I worked with <strong>Cypress</strong> for end-to-end testing, creating automated test scripts to ensure the reliability and efficiency of the software. This involved writing, executing, and debugging tests to validate various user interactions and flows within the applications.
            </p>
            <p>
                In addition to testing, I contributed to <strong>web development</strong> projects using <strong>Angular</strong>. My responsibilities included developing dynamic and responsive user interfaces, implementing front-end components, and collaborating with team members to enhance application functionality. This experience strengthened my skills in JavaScript, TypeScript, and modern front-end frameworks.
            </p>
        </li>
    </ul>
    `,
];

const textsToAudio = [
    "Who am I? I am a senior undergraduate student in the Department of Computer Science and Engineering at the University of Ioannina, currently just one class away from graduation. My primary interests lie in systems programming, application development, and artificial intelligence, where I have gained a strong blend of theoretical knowledge and hands-on experience. Throughout my studies and personal projects, I’ve worked to develop skills in software system design, machine learning applications, and creating innovative solutions to computational challenges.",
    "Experience. Internship at Archimedes Unit, Athenah Research Center. I am currently engaged in an internship at the Archimedes Unit, Athenah Research Center. More details about my work and contributions will be shared soon. Internship at Terracom SA. During my internship at Terracom SA, I focused on two main areas: automation testing and web development. I worked with Cypress for end-to-end testing, creating automated test scripts to ensure the reliability and efficiency of the software. This involved writing, executing, and debugging tests to validate various user interactions and flows within the applications. In addition to testing, I contributed to web development projects using Angular. My responsibilities included developing dynamic and responsive user interfaces, implementing front-end components, and collaborating with team members to enhance application functionality. This experience strengthened my skills in JavaScript, TypeScript, and modern front-end frameworks"
];

let currentIndex = -1;
let utterance = new SpeechSynthesisUtterance();
const btns = document.getElementById('btns');

// Update the text and cancel any ongoing speech
function updateText() {
    speechSynthesis.cancel();
    document.getElementById('text-content').innerHTML = texts[currentIndex];
}

// Move to the next text
function nextText() {
    btns.style.visibility = 'visible';
    if (currentIndex < texts.length - 1) {
        currentIndex++;
        updateText();
    }
}

// Move to the previous text
function prevText() {
    if (currentIndex > 0) {
        currentIndex--;
        updateText();
    }
}

// Start speaking the text
function speak() {
    // Only initiate speech synthesis if it's triggered by a user interaction (click/tap)
    if ('speechSynthesis' in window && currentIndex >= 0) {
        speechSynthesis.cancel();  // Cancel any ongoing speech

        const text = textsToAudio[currentIndex];
        utterance.text = text;
        utterance.lang = 'en-US'; 
        utterance.pitch = 1;      
        utterance.rate = 1;       
        utterance.volume = 1;     

        // Speak the text after cancelling any previous speech
        speechSynthesis.speak(utterance);
    }
}

// Pause the speech
function pause() {
    speechSynthesis.cancel();
}

// Restart speech (start from the beginning)
function restart() {
    speechSynthesis.cancel();
    const text = textsToAudio[currentIndex];
    utterance.text = text;
    speechSynthesis.speak(utterance);
}

// Ensure that speech is always triggered by user interaction (important for Android)
document.getElementById('nextButton').addEventListener('click', () => nextText());
document.getElementById('prevButton').addEventListener('click', () => prevText());
document.getElementById('speakButton').addEventListener('click', () => speak());
document.getElementById('pauseButton').addEventListener('click', () => pause());
document.getElementById('restartButton').addEventListener('click', () => restart());
