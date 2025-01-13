//Quiz will show one question at a time
document.addEventListener("DOMContentLoaded", function() {
    const questionBoxes = document.querySelectorAll('.question-box');
    let currentQuestionIndex = 0;


    questionBoxes.forEach((box, index) => {
        box.style.display = 'none';
    });

    questionBoxes[currentQuestionIndex].style.display = 'block';

    function showNextQuestion() {

        questionBoxes[currentQuestionIndex].style.display = 'none';

        currentQuestionIndex++;

        if (currentQuestionIndex < questionBoxes.length) {
            questionBoxes[currentQuestionIndex].style.display = 'block';
        }
    }

    document.querySelectorAll('.next-btn').forEach((button) => {
        button.addEventListener('click', showNextQuestion);
    });
});
