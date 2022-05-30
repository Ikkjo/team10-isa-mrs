<template>
    <div class="picture-div">
        <file-pond
        name="test"
        ref="pond"
        label-idle="Drop images here or <span class='filepond--label-action'>Browse</span>"
        allow-multiple="true"
        accepted-file-types="image/jpeg, image/png"
        v-bind:files="pictures"
        :allowReorder="true"
        :maxFiles="10"
        :onupdatefiles="updatedFiles"
        :onreorderfiles="updatedFiles"
        :allowFileEncode="true"
        />
    </div>
</template>

<script>
// Import Vue FilePond
import vueFilePond from "vue-filepond";

// Import FilePond styles
import "filepond/dist/filepond.min.css";

// Import FilePond plugins
// Please note that you need to install these plugins separately

// Import image preview plugin styles
import "filepond-plugin-image-preview/dist/filepond-plugin-image-preview.min.css";

// Import image preview and file type validation plugins
import FilePondPluginFileValidateType from "filepond-plugin-file-validate-type";
import FilePondPluginImagePreview from "filepond-plugin-image-preview";
import FilePondPluginFileEncode from "filepond-plugin-file-encode";

// Create component
const FilePond = vueFilePond(
  FilePondPluginFileValidateType,
  FilePondPluginImagePreview,
  FilePondPluginFileEncode
);
export default {
    name: 'RentalEntityPictureInput',
    props: {
        defaultPictures: {
            Array,
            default: []
        }
    },
    components: {
        FilePond,
    },
    data() {
        return {
            pictures: [],
        }
    },
    methods: {
        updatedFiles(files) {
            console.log(files);
            this.$emit('updated', files);
        },
        setPictures(pictures) {
            this.pictures = pictures;
        }
    },
    mounted() {
        if (this.props !== [])
            this.pictures = [...this.defaultPictures]
    }
}
</script>

<style>

</style>