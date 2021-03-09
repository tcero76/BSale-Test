// Estado de la paginación
var state = {categories: [], products: []}
// Container principal donde se aloja el catálogo.
var container;
// Número de ítems por fila
const nItems = 3;

// Listener inicial. Se asignan funciones al input de búsqueda y se setean 
// valores iniciales.
window.addEventListener('load', function(e) {
    container = document.getElementById("idcontainer");
    var searchButton = document.getElementById("id__search__button");
    var searchInput = document.getElementById("id__search__input");
    searchButton.addEventListener('click', e => {
        e.preventDefault();
        getProductsByName(searchInput.value)
     });
    searchInput.addEventListener('keydown', e => {
        if(e.key=='Enter') {
            e.preventDefault();
            getProductsByName(searchInput.value)
        }
    });
    getProducts();
});

//  request de  todos los productos.
function getProducts() {
    fetch(`/products`)
        .then(res => res.json())
        .then(data => renderCatalogo(data))
        .catch(err => console.log(err))
}

//  request de los productos filtrados por nombre
function getProductsByName(input) {
    if(input==""){
        getProducts();
    } else {
        fetch(`/${input.toUpperCase()}/products/`)
            .then(res => res.json())
            .then(data => {
                if(data.length==0) {
                    renderInfo("No hay coincidencias");
                    return;
                }
                return renderCatalogo(data)
            })
            .catch(err => console.log(err))
    }
}

// render mensaje de respuesta
function renderInfo(msg) {
    container.innerHTML = `<div class="alert--info">${msg}</div>`;
}

// renderiza el catálogo de productos.
function renderCatalogo(data) {
    container.innerHTML = '';
    state.categories = Array.from(new Set(data.map(d=>d.category)))
        .sort((c1,c2) => {
            if(c1<c2) {return -1};
            if(c2>c2) {return 1};
            return 0;
        })
        .map(c=>({ name:c,
                   id: data.filter(d=>d.category==c)[0].idcategory,
                   totalItems: data.filter(d=>d.category==c).length,
                   page:1,
                   npage: ~~(data.filter(d=>d.category==c).length/3)
                 }))
    state.products = data;
    renderCategoria();
}

// Renderiza una categoría de productos.
function renderCategoria() {
    state.categories
       .forEach(c=> {
           container.innerHTML += `<H2 class="container__category">${c.name.toUpperCase()}</H2>`
           container.innerHTML += `<div id="category${c.id}" class="catalogo"></div>`
           renderCategoryItem(c);
       })
}

// Renderiza los productos de una categoría.
function renderCategoryItem(c) {
   var category = document.getElementById(`category${c.id}`);
   category.innerHTML = renderIconLeft(c);
   state.products
       .filter(d => d.category == c.name)
       .sort((d1,d2) => {
       if(d1.name<d2.name) {
           return -1;
       } else if(d1.name>d2.name) {
           return 1;
       } else {
           return 0;
       }
       })
       .slice((c.page-1)*nItems,c.page*nItems>=c.totalItems?c.totalItems:c.page*nItems)
       .forEach(d => {
           category.innerHTML += renderCard(d);
       });
   category.innerHTML += renderIconRight(c);
}

// Renderiza una carta con información de un producto.
function renderCard(d) {
    return `<div class="card"><div class="card__image" style="background-image:url('${d.url_image}')"></div><div class="card__name"><label class="card__product-name">${d.name}</label></div><div class="card__detail">$ ${d.price.toFixed(0)}<span class="card__icon"><svg class="card__add__icon"></svg></span></div></div>`;
}

// Renderiza ícono de paginación izquierdo.
function renderIconLeft(c) {
    var opacity = (c.page>1)?"1":"0";
    return `<div id="previousPage${c.id}" style="opacity:${opacity} " onclick="onClickPrevious(event, ${c.id})" class="previousPage__icon-box${(c.page>=c.npage)?' disable':''}"></div>`
}

// Renderiza ícono de paginación derecho.
function renderIconRight(c) {
    var opacity = (c.page<=c.npage && c.totalItems>c.page*nItems)?"1":"0";
    return `<div id="nextPage${c.id}" style="opacity:${opacity}" onclick="onClickNext(event, ${c.id})" class="nextPage__icon-box${(c.page>=c.npage)?' disable':''}"></div>`
}

// Función ejecutada en el evento Click de la flecha previo
function onClickPrevious(e,id) {
    let c = state.categories.find(c=>c.id==id)
    var previous = document.getElementById(`previousPage${c.id}`)
    if(c.page>1) c.page--
    renderCategoryItem(c);
}

// Función ejecutada en el evento Click de la flecha siguiente
function onClickNext(e,id){
    let c = state.categories.find(c=>c.id==id)
    var next = document.getElementById(`nextPage${c.id}`)
    if(c.page<=c.npage && c.totalItems>c.page*nItems) c.page++
    renderCategoryItem(c);
}