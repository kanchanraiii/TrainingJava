const questions = [
    {
        question: "Which planet is known as the Red Planet?",
        answers: [
            { text: "Venus", correct: false },
            { text: "Mars", correct: true },
            { text: "Jupiter", correct: false },
            { text: "Saturn", correct: false },
        ]
    },
    {
        question: "What is the largest moon of Saturn?",
        answers: [
            { text: "Ganymede", correct: false },
            { text: "Europa", correct: false },
            { text: "Titan", correct: true },
            { text: "Io", correct: false },
        ]
    },
    {
        question: "How many planets are in our solar system?",
        answers: [
            { text: "7", correct: false },
            { text: "8", correct: true },
            { text: "9", correct: false },
            { text: "10", correct: false },
        ]
    },
    {
        question: "What is the center of our solar system?",
        answers: [
            { text: "Earth", correct: false },
            { text: "The Moon", correct: false },
            { text: "The Sun", correct: true },
            { text: "Alpha Centauri", correct: false },
        ]
    },
    {
        question: "Which of these is a dwarf planet?",
        answers: [
            { text: "Mercury", correct: false },
            { text: "Pluto", correct: true },
            { text: "Neptune", correct: false },
            { text: "Uranus", correct: false },
        ]
    },
];


const startScreen = document.getElementById('start-screen');
const quizScreen = document.getElementById('quiz-screen');
const resultScreen = document.getElementById('result-screen');
const startBtn = document.getElementById('start-btn');
const restartBtn = document.getElementById('restart-btn');
const questionText = document.getElementById('question-text');
const answerButtonsDiv = document.getElementById('answer-buttons');
const questionNumberSpan = document.getElementById('question-number');
const scoreSpan = document.getElementById('score');
const finalScoreP = document.getElementById('final-score');
const nextBtn = document.getElementById('next-btn');
const progressBar = document.getElementById('progress');


let currentQuestionIndex = 0;
let score = 0;
let answerSelected = false;



function startQuiz() {
  
    startScreen.classList.add('hidden');
    quizScreen.classList.remove('hidden');
    resultScreen.classList.add('hidden');
    currentQuestionIndex = 0;
    score = 0;
    scoreSpan.textContent = `Score: ${score}`;
    nextBtn.classList.add('hidden');
    showQuestion();
}

function showQuestion() {
    
    answerSelected = false;
    nextBtn.classList.add('hidden');
    answerButtonsDiv.innerHTML = ''; 

  
    const currentQuestion = questions[currentQuestionIndex];
    questionNumberSpan.textContent = `Question ${currentQuestionIndex + 1} of ${questions.length}`;
    questionText.textContent = currentQuestion.question;

    
    const progressPercent = (currentQuestionIndex / questions.length) * 100;
    progressBar.style.width = `${progressPercent}%`;

   
    currentQuestion.answers.forEach(answer => {
        const button = document.createElement('button');
        button.innerHTML = answer.text;
        button.classList.add('btn', 'answer-btn');
        
        button.addEventListener('click', () => selectAnswer(button, answer.correct));
        answerButtonsDiv.appendChild(button);
    });
}

function selectAnswer(selectedBtn, isCorrect) {
    if (answerSelected) return;
    answerSelected = true;
    
  
    if (isCorrect) {
        score++;
        selectedBtn.classList.add('correct');
        scoreSpan.textContent = `Score: ${score}`;
    } else {
        selectedBtn.classList.add('incorrect');
       
        Array.from(answerButtonsDiv.children).forEach(button => {
            const answer = questions[currentQuestionIndex].answers.find(a => a.text === button.innerHTML);
            if (answer.correct) {
                button.classList.add('correct');
            }
        });
    }

  
    Array.from(answerButtonsDiv.children).forEach(button => {
        button.disabled = true;
    });

 
    nextBtn.classList.remove('hidden');
}

function handleNextButton() {
    currentQuestionIndex++;
    if (currentQuestionIndex < questions.length) {
        showQuestion();
    } else {
        showResult();
    }
}

function showResult() {
   
    quizScreen.classList.add('hidden');
    resultScreen.classList.remove('hidden');
    finalScoreP.textContent = `${score} / ${questions.length}`;
    progressBar.style.width = '100%';
}


startBtn.addEventListener('click', startQuiz);
restartBtn.addEventListener('click', startQuiz);
nextBtn.addEventListener('click', handleNextButton);
