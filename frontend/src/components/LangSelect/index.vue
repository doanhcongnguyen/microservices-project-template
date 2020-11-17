<template>
  <el-dropdown trigger="click" class="international" @command="handleSetLanguage">
    <div>
      <svg-icon class-name="international-icon" icon-class="language" />
    </div>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item :disabled="language==='en'" command="en">English</el-dropdown-item>
      <el-dropdown-item :disabled="language==='vi'" command="vi">Tiếng Việt</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
import { showSuccessMessage } from '@/utils/commons'

export default {
  computed: {
    language() {
      return this.$store.getters.language
    }
  },
  methods: {
    handleSetLanguage(lang) {
      this.$i18n.locale = lang
      this.$store.dispatch('app/setLanguage', lang)
      showSuccessMessage()
    }
  }
}
</script>

<style scoped>
.international-icon {
  font-size: 23px;
  cursor: pointer;
  vertical-align: -8px!important;
}
</style>

