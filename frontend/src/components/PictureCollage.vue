<template>
  <div id="picture-collage">
      <img v-show="!showAll" v-for="(pic, i) in pictures.slice(0, 5)" :key="i" :src="pic" class="photo" :class="i === 0 ? 'cover-picture' : ''">
      <button class="btn" @click="toggleShowAll">See All & Edit</button>
      <RentalEntityPictureInput ref="pictureInput" v-show="showAll" class="picture-input"/>
  </div>
</template>

<script>
import RentalEntityPictureInput from '@/components/RentalEntityPictureInput.vue'
export default {
    name: 'PictureCollage',
    components: {
      RentalEntityPictureInput,
    },
    props: ['pictures'],
    data() {
      return {
        showAll: false,
      }
    },
    methods: {
        toggleShowAll() {
          if (!this.showAll) 
            this.$refs.pictureInput.setPictures(this.pictures)
          this.showAll = !this.showAll
        }
    }
}
</script>

<style scoped>

#picture-collage {
  display: grid;
  width: 100%;
  grid-template-rows: repeat(2, 1fr);
  grid-template-columns: repeat(4, 1fr);
  grid-auto-flow: dense;
  position: relative;
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

.btn {
  position: absolute;
  bottom: 0;
  right: 0;
  margin-right: 6px;
  margin-bottom: 6px;
}

.picture-input {
  grid-column: span 4;
  grid-row: span 2;
}

</style>