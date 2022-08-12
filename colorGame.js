let square=document.querySelector('.child');
let body=document.querySelector('body');

var colors=['lime','cyan','purple'];
var boxColor='white';
var returnedColor='red';


window.addEventListener('load',()=>{
    square.style.position="absolute";
    square.style.background="white";
    body.style.background="#222"
});

window.addEventListener('keydown',(e)=>{
    switch (e.key) {
        case 'l':
            square.style.background="lime";
            boxColor="lime";
            console.log(boxColor);
            break;
        case'c':
            square.style.background="cyan";
            boxColor="cyan";
            console.log(boxColor);
            break;
        case'p':
            square.style.background="purple";
            boxColor="purple";
            console.log(boxColor);
        break;
    }
});

function picker(){

    var random=Math.random();
    var final=Math.floor(random*colors.length);
    returnedColor=colors[final];
    body.style.background=returnedColor;
    
   
}

setTimeout(picker,3000);






