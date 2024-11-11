document.addEventListener("DOMContentLoaded", function() {
    let currentIndex = 0;
    const texts = [
        "Who am I? I am a senior undergraduate student in the Department of Computer Science and Engineering at the University of Ioannina, maintaining a GPA of 7.61/10 and currently just one class away from graduation. My primary interests lie in systems programming, application development, and artificial intelligence.",
        "Experience. Internship at Terracom SA. During my internship at Terracom SA, I focused on two main areas: automation testing and web development. I worked with Cypress for end-to-end testing, creating automated test scripts."
    ];

    const textsToAudio = [
        "Who am I? I am a senior undergraduate student in the Department of Computer Science and Engineering at the University of Ioannina, maintaining a GPA of 7.61/10 and currently just one class away from graduation. My primary interests lie in systems programming, application development, and artificial intelligence.",
        "Experience. Internship at Terracom SA. During my internship at Terracom SA, I focused on two main areas: automation testing and web development. I worked with Cypress for end-to-end testing, creating automated test scripts."
    ];

    let utterance = new SpeechSynthesisUtterance();
    const btns = document.getElementById('btns');
    const speakButton = document.getElementById('speakButton');
    const pauseButton = document.getElementById('pauseButton');
    const restartButton = document.getElementById('restartButton');
    const nextButton = document.getElementById('nextButton');
    const prevButton = document.getElementById('prevButton');
    const textContent = document.getElementById('text-content');
    

    // Update the text
    function updateText() {
        btns.style.visibility = 'visible';
        textContent.innerHTML = `<h1 class="headerTitle">Page ${currentIndex + 1}</h1><p>${texts[currentIndex]}</p>`;
        speechSynthesis.cancel();
    }

    // Start speaking the text
    function speak() {
        // Only initiate speech synthesis if it's triggered by a user interaction
        if ('speechSynthesis' in window && currentIndex >= 0) {
            speechSynthesis.cancel(); // Cancel any ongoing speech

            const text = textsToAudio[currentIndex];
            utterance.text = text;
            utterance.lang = 'en-US';
            utterance.pitch = 1;
            utterance.rate = 1;
            utterance.volume = 1;

            // Speech synthesis
            speechSynthesis.speak(utterance);
        }
    }

    // Pause speech
    function pause() {
        speechSynthesis.pause();
    }

    // Restart speech
    function restart() {
        speechSynthesis.cancel();
        speak();
    }

    // Move to the next text
    function nextText() {
        currentIndex = (currentIndex + 1) % texts.length; // Loop back to first page
        updateText();
    }

    // Move to the previous text
    function prevText() {
        currentIndex = (currentIndex - 1 + texts.length) % texts.length; // Loop back to last page
        updateText();
    }

    // Attach event listeners to buttons
    speakButton.addEventListener('click', function() {
        speak();
    });

    pauseButton.addEventListener('click', function() {
        pause();
    });

    restartButton.addEventListener('click', function() {
        restart();
    });

    nextButton.addEventListener('click', function() {
        nextText();
    });

    prevButton.addEventListener('click', function() {
        prevText();
    });

    // Initialize the first page
    updateText();
});
