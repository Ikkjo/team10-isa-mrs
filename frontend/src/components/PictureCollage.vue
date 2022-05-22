<template>
  <div id="picture-collage">
      <img v-for="(pic, i) in pictures.slice(0, 5)" :key="i" :src="pic" class="photo" :class="i === 0 ? 'cover-picture' : ''">
  </div>
</template>

<script>
export default {
    name: 'PictureCollage',
    props: ['pictures'],
}
</script>

<style scoped>

#picture-collage {
  display: grid;
  width: 100%;
  grid-template-rows: repeat(2, 1fr);
  grid-template-columns: repeat(4, 1fr);
  grid-auto-flow: dense;
}

#picture-collage > *:first-child {
  grid-column: span 2;
  grid-row: span 2;
}

.photo {
    object-fit: cover;
    background-repeat: no-repeat;
    background-position: center; 
    background-size: cover;
    width: 100%;
    height: 100%;
}

</style>