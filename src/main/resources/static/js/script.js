// These are our variables, please get these from the HTML file and store them in const
const menuIcon = document.querySelector('.menu-icon');
const linksDiv = document.querySelector('.links-div');

menuIcon.addEventListener('click', () => { //When menu is clicked show or hide these links
    linksDiv.classList.toggle('active');
});

document.addEventListener('click', (e) => {//when click is outside menu close the links
    if (!linksDiv.contains(e.target) && e.target !== menuIcon) {
        linksDiv.classList.remove('active'); //we remove to hide links
    }
});

menuIcon.addEventListener('click', (e) => {
    e.stopPropagation(); //keep menu open when clicking on menuIcon
});
