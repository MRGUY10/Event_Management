const contain = $('.contain')
const svgs = $('.card_icons svg')
const card = $('.card')
const after =$('.after_card')
svgs.click(function (e){
console.log('moon');
  const val = e.target.getAttribute('value');
  if(val == 1) box()
  else if(val == 2){
    card.addClass('hidden')
    contain.toggleClass('contain_grid')
    contain.toggleClass('contain_flex')
    after.removeClass('hidden')
  }

})
function box(){
  document.querySelector('.container').style.display= 'block'
  document.querySelector('.overlay').style.display= 'block'
}
function unbox(){
  document.querySelector('.container').style.display= 'none'
  document.querySelector('.overlay').style.display= 'none'
}

function box2(){
  document.querySelector('.suc_container').style.display= 'block'
  document.querySelector('.container').style.display= 'none'
}

document.addEventListener('keydown', function (e){
  // console.log(e.key)
  if(e.key == 'Escape'){
    document.querySelector('.container').style.display= 'none'
    document.querySelector('.suc_container').style.display= 'none'
    document.querySelector('.overlay').style.display= 'none'
  }
})