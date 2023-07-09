var sidebar = document.getElementById('sidebar');
var opn = false;
var tgl = document.getElementById("toggleButton");
var isClass1 = false;
tgl.classList.add("class1");


function toggleSidebar() {
  sidebar.classList.toggle('open');
  opn = !opn;
  console.log(opn)
}


tgl.addEventListener("click", function() {
  if (isClass1) {
    tgl.innerHTML = '<i class="fa fa-caret-right"></i>';
    tgl.classList.remove("class2");
    tgl.classList.add("class1");
  } else {
    tgl.innerHTML = '<i class="fa fa-caret-left"></i>';
    tgl.classList.remove("class1");
    tgl.classList.add("class2");
  }
  isClass1 = !isClass1;
});

