if (document.readyState == 'loading') document.addEventListener('DOMContentLoaded', ready)      //make sure page is loaded
else ready()

function ready()        //add all eventListeners to all necessary buttons when page finishes loading                                        
{
    var addButtons = document.getElementsByClassName('add')
    for(var i = 0; i < addButtons.length; i++)
    {
        var button = addButtons[i]
        button.addEventListener('click', addToCartClicked)
    }

    document.getElementsByClassName('checkout')[0].addEventListener('click', checkoutClicked)
}

function openCart()     //opens the cart sidebar
{
    document.getElementById("cart").style.width = "30%";
    document.getElementById("grid").style.marginRight = "calc(2em + 30%)";
    document.getElementById("menu").style.marginRight = "30%";
    document.getElementById("icon").style.display = "none";
    
    var x = document.getElementsByClassName("cart-item");
    
    for(var i = 0; i < x.length; i++)
    {
       x[i].style.display = "flex";
    }
}

function closeCart()    //closes the cart sidebar
{
    document.getElementById("cart").style.width = "0";
    document.getElementById("grid").style.marginRight = "2em";
    document.getElementById("menu").style.marginRight = "0";
    document.getElementById("icon").style.display = "initial";
    
    var x = document.getElementsByClassName("cart-item");
    
    for(var i = 0; i < x.length; i++)
    {
       x[i].style.display = "initial";
    }
}

function addToCartClicked(event)    //grabs the corresponding item name and price when Add To Cart is clicked and adds it to cart
{
    var buttonClicked = event.target
    var item = buttonClicked.parentElement
    var itemName = item.getElementsByClassName('shop-item')[0].innerText
    var itemPrice = item.getElementsByClassName('shop-price')[0].innerText
    addToCartItem(itemName, itemPrice)
    updateCartTotal()
}

function addToCartItem(name, price)     //the functions that adds the item to the cart sidebar
{
    var cartItem = document.createElement('div')
    cartItem.classList.add('cart-item')
    var cartCart = document.getElementsByClassName('cart-actual')[0]
    var itemNames = document.getElementsByClassName('item-name')
    for(var i = 0; i < itemNames.length; i++)
    {
        if(itemNames[i].innerText == name)
        {
            alert('This item is already in the cart')
            return
        }
    }
    var cartItemDeets =
        `
        <div class="item-name cart-column">
            <span>${name}</span>
        </div>
        <span class="item-price cart-column">${price}</span>
        `
    if(name == 'Hoodie' || name == 'T-Shirt')
    {
        cartItemDeets = cartItemDeets +
            `
            <div class="size">
                <select name="size" class="item-size cart-column">
                    <option value="XS">XS</option>
                    <option value="S">S</option>
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                </select>
            </div>
            `
    }
    cartItemDeets = cartItemDeets +
        `
        <div class="item-qty cart-column">
            <input class="qty" type="number" value="1">
            <button class="remove" type="button">REMOVE</button>
        </div>
        `
    cartItem.innerHTML = cartItemDeets
    cartCart.append(cartItem)
    cartItem.getElementsByClassName('remove')[0].addEventListener('click', removeFromCartClicked)   //adds eventListeners to quantity and remove
    cartItem.getElementsByClassName('qty')[0].addEventListener('change', qtyChanged)                //      buttons to the item added to cart

    var x = document.getElementsByClassName("cart-item");
    if(document.getElementById("icon").style.display === "none")
    {
        x[x.length-1].style.display = "flex";
    }
    else
    {
        x[x.length-1].style.display = "initial";
    }
}

function qtyChanged(event)      //prevents quantity from being less than 1
{
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) 
    {
        input.value = 1
        alert('You cannot buy zero or less items.')
    }
    updateCartTotal()
}

function removeFromCartClicked(event)   //removes item from cart sidebar
{
    var buttonClicked = event.target
    buttonClicked.parentElement.parentElement.remove()
    updateCartTotal()
}

function checkoutClicked()      //removes all items from cart
{
    var cartStuff = document.getElementsByClassName('cart-actual')[0]
    
    var cartOut = document.createElement('form')
    cartOut.setAttribute('action', 'printReciept')
    cartOut.setAttribute('method', 'get')
    var input
    var inputName
    var inputPrice
    var inputSize
    var inputQty
    var inputSub
    
    if(!cartStuff.hasChildNodes())
    {
        alert('There are no items in your cart.')
        return
    }
    
    for(var i = 1; cartStuff.hasChildNodes(); i++)
    {
        input = cartStuff.firstChild.getElementsByClassName('item-name')[0].innerText
        inputName = document.createElement('input')
        inputName.setAttribute('type', 'hidden')
        inputName.setAttribute('name', 'name' + i)
        inputName.setAttribute('value', input)
        cartOut.appendChild(inputName)
        
        input = cartStuff.firstChild.getElementsByClassName('item-price')[0].innerText
        inputPrice = document.createElement('input')
        inputPrice.setAttribute('type', 'hidden')
        inputPrice.setAttribute('name', 'price' + i)
        inputPrice.setAttribute('value', input)
        cartOut.appendChild(inputPrice)
        
        input = cartStuff.firstChild.getElementsByClassName('item-size')[0]
        if(input == !null)
        {
            inputSize = document.createElement('input')
            inputSize.setAttribute('type', 'hidden')
            inputSize.setAttribute('name', 'size' + i)
            inputSize.setAttribute('value', input.value)
            cartOut.appendChild(inputSize)
        }
        
        input = cartStuff.firstChild.getElementsByClassName('qty')[0].value
        inputQty = document.createElement('input')
        inputQty.setAttribute('type', 'hidden')
        inputQty.setAttribute('name', 'qty' + i)
        inputQty.setAttribute('value', input)
        cartOut.appendChild(inputQty)
        
        document.getElementsByTagName('body')[0].appendChild(cartOut)
        cartStuff.removeChild(cartStuff.firstChild)
    }
    cartOut.submit()
    alert('Thank you for Shopping')
    updateCartTotal() 
}

function updateCartTotal()      //updates the total price at the bottom of the cart
{
    var cartActual = document.getElementsByClassName('cart-actual')[0]
    var cartItems = cartActual.getElementsByClassName('cart-item')
    var total = 0
    for(var i = 0; i < cartItems.length; i++)
    {
        var cartItem = cartItems[i]
        var itemPrice = cartItem.getElementsByClassName('item-price')[0]
        var itemQty = cartItem.getElementsByClassName('qty')[0]
        var price = parseFloat(itemPrice.innerText.replace('$', ''))
        var qty = itemQty.value
        total = total + (price * qty)
    }
    document.getElementsByClassName('total-price')[0].innerText = '$' + total.toFixed(2)
}