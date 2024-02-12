package com.burndown.dto;

import java.util.Set;
import com.burndown.core.entity.Sprint;
import com.burndown.core.entity.Story;


public class SprintBacklogDto {

		private Long id;
		private Set<StoryDto> story;
		private SprintDto sprint;

		public SprintBacklogDto(SprintDto sprint) {
			super();
			this.sprint = sprint;
		}

		public SprintBacklogDto(Long id, Set<StoryDto> story, SprintDto sprint) {
			super();
			this.id = id;
			this.story = story;
			this.sprint = sprint;
		}

		public SprintBacklogDto(Set<StoryDto> story, SprintDto sprint) {
			super();
			this.story = story;
			this.sprint = sprint;
		}

		public SprintDto getSprint() {
			return sprint;
		}

		public void setSprint(SprintDto sprint) {
			this.sprint = sprint;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Set<StoryDto> getStory() {
			return story;
		}

		public void setStory(Set<StoryDto> story) {
			this.story = story;
		}
		
		
		
}
