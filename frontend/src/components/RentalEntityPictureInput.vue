<template>
    <div class="picture-div">
        <picture-input 
        ref="pictureInput"
        width="500" 
        height="300" 
        accept="image/jpeg,image/png" 
        size="10" 
        button-class="btn"
        :custom-strings="{
            upload: '<h1>Bummer!</h1>',
            drag: 'Drag your 🏡 cover photo here'
        }"
        @change="onChange"/>
        <picture-input v-for="i in 10" :key="i" v-show="i <= 4 ? true : Number(pictures.length) >= i ? true : false"
        ref="pictureInput"
        width="250" 
        height="150" 
        accept="image/jpeg,image/png" 
        size="10" 
        button-class="btn"
        :custom-strings="{
            upload: '<h1>Bummer!</h1>',
            drag: '<span class=\'material-icons-round\'>file_upload</span>'
        }"
        @change="onChange"/>
    </div>
</template>

<script>
import PictureInput from 'vue-picture-input';
export default {
    name: 'RentalEntityPictureInput',
    components: {
        PictureInput,
    },
    data() {
        return {
            pictures: [],
        }
    },
    methods: {
        onChange (picture) {
            console.log('New picture selected!');
            if (picture) {
                console.log('Picture loaded.');
                this.pictures.push(picture);
                console.log(this.pictures.length);
                this.$nextTick(function () {
                    // DOM updated
                    window.dispatchEvent(new Event('resize'));
                });
                this.$emit('updated', this.pictures);
            } else {
                console.log('FileReader API not supported: use the <form>, Luke!');
            }
        }
    }
}
</script>

<style>
.picture-div {
    display: grid;
    justify-content: center;
    grid-template-columns: 200px 200px;
    max-height: 600px;
    overflow-y: auto;
    overflow-x: hidden;

}

.picture-div :first-child {
    grid-column: 1/-1;
}

.preview-container {
    margin: 0;
}
</style>