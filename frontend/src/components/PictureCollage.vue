<template>
  <div id="picture-collage">
      <img v-for="(pic, i) in pictures.slice(0, 5)" :key="i" :src="pic" class="photo" :class="i === 0 ? 'cover-picture' : ''">
      <button class="btn" @click="showAll = !showAll">See All & Edit</button>
      <portal to="body">
            <!-- use the modal component, pass in the prop -->
            <PictureUpdateModal
            v-if="showAll"
            :show="showAll"
            @close="showAll=false"
            @save="savePictures"
            class="modal">
            <template #body>
              <RentalEntityPictureInput ref="pictureInput" :defaultPictures="pictures" @updated="updatePictures" v-show="showAll" class="picture-input"/>
            </template>
            </PictureUpdateModal>
      </portal>
  </div>
</template>

<script>
import RentalEntityPictureInput from '@/components/RentalEntityPictureInput.vue'
import PictureUpdateModal from '@/components/PictureUpdateModal.vue'
export default {
    name: 'PictureCollage',
    components: {
      RentalEntityPictureInput,
      PictureUpdateModal,
    },
    props: ['pictures'],
    data() {
      return {
        showAll: false,
        updatedPictures: []
      }
    },
    methods: {
        updatePictures(pictures) {
          this.updatedPictures = pictures
        },
        savePictures() {
            if (this.updatedPictures.length < 5 || this.updatedPictures.length > 10)
              alert("You need to add between 5 and 10 pictures.")
            else {
              this.$emit('update', this.updatedPictures)
              this.showAll = !this.showAll
            }
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