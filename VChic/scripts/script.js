import { items } from './items.js'; 

const searchBar = document.getElementById('search_bar');
const itemContainer = document.querySelector('.item_container');

export function findItem() {
    const searchText = searchBar.value.toLowerCase();

    const itemExists = items.some(item => 
        item.category.toLowerCase() === searchText || 
        item.brand.toLowerCase() === searchText
    );

    if (itemExists) {
        showItems(searchText);
    } else {
        itemContainer.innerHTML = "<h1 id='no_results'>No results found</h1>";
    }
}

function showAllItems() {
    itemContainer.innerHTML = ""; 

    items.forEach(item => {
        itemContainer.innerHTML += `
            <div class="item_card">
                <img src="image.png" alt="${item.category}">
                <span class="item_title">${item.brand}</span>
                <span class="item_price">${item.price}</span>
                <i class="fa-regular fa-heart" id="hearted"></i>
            </div>`;
    });
}

function showItems(searchInput) {
    itemContainer.innerHTML = ""; 

    items.forEach(item => {
        if (item.category.toLowerCase() === searchInput || item.brand.toLowerCase() === searchInput) {
            itemContainer.innerHTML += `
                <div class="item_card">
                    <img src="image.png" alt="${item.category}">
                    <span class="item_title">${item.brand}</span>
                    <span class="item_price">${item.price}</span>
                </div>`;
        }
    });
}

function loadCategoryItems(category) {
    itemContainer.innerHTML = ""; 

    const filteredItems = items.filter(item => item.category === category.toLowerCase());

    if (filteredItems.length > 0) {
        filteredItems.forEach(item => {
            itemContainer.innerHTML += `
                <div class="item_card">
                    <img src="image.png" alt="${item.category}">
                    <span class="item_title">${item.brand}</span>
                    <span class="item_price">${item.price}</span>
                </div>`;
        });
    } else {
        itemContainer.innerHTML = "<h1 id='no_results'>No items found in this category</h1>";
    }
}

export function toggleMenu() {
    const column1 = document.getElementById("column1");
    const body = document.body;
    const menuButtonIcon = document.querySelector("#menuButton i");

    column1.classList.toggle("overlay");

    if (column1.classList.contains("overlay")) {
        body.style.overflowY = "hidden"; 
        menuButtonIcon.classList.add("rotate"); 
        menuButtonIcon.classList.remove("fa-bars"); 
        menuButtonIcon.classList.add("fa-times");
    } else {
        body.style.overflowY = ""; 
        menuButtonIcon.classList.remove("rotate"); 
        menuButtonIcon.classList.remove("fa-times"); 
        menuButtonIcon.classList.add("fa-bars");
    }
}

document.addEventListener("DOMContentLoaded", () => {
    showAllItems(); 
    document.getElementById("menuButton").addEventListener("click", toggleMenu);
    document.querySelector(".fa-search").addEventListener("click", findItem);

    searchBar.addEventListener("keydown", (event) => {
        if (event.key === 'Enter') { 
            findItem(); 
        }
    });    

    document.querySelectorAll('.category-item').forEach(item => {
        item.addEventListener('click', (event) => {
            const category = event.target.getAttribute('data-category'); 
            loadCategoryItems(category); 
        });
    });

    document.getElementById('bottom_search').addEventListener('click', function() {
        const navbar = document.getElementById('navbar');
        navbar.scrollIntoView({ behavior: 'smooth' });
    });
    
});
